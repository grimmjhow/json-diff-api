package br.com.waes.json.diff.rest;

import br.com.waes.json.diff.model.DiffSide;
import br.com.waes.json.diff.model.JsonComparison;
import br.com.waes.json.diff.repositories.JsonComparisonRepository;
import br.com.waes.json.diff.utils.IntegrationBaseTest;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Stream;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.is;

class JsonComparisonRestIT extends IntegrationBaseTest {

    @Autowired
    private JsonComparisonRepository repository;

    @Test
    @DisplayName("should throw exception when base64 encode is empty")
    public void throwExceptionWhenBase64EncodeIsEmpty() {
        this.repository.deleteAll();
        Response response = given().log().all()
                .post("/v1/diff/10/left");

        response.then().log().all()
                .statusCode(200)
                .body("$",hasKey("message"))
                .body("$",hasKey("valid"))
                .body("valid",is(false))
                .body("message", is("content base64 encode could not be empty"));
    }

    @Test
    @DisplayName("should throw exception when json-comparison already exist")
    public void shouldSaveLefDiff() {
        this.repository.deleteAll();
        this.repository.save(new JsonComparison(11, DiffSide.LEFT,"eyJuYW1lIjogImZlbGlwZSBtb250ZWlybyJ9"));

        Response response = given().log().all()
                .body("eyJuYW1lIjogImZlbGlwZSBtb250ZWlybyJ9")
                .post("/v1/diff/11/left");

        response.then().log().all()
                .statusCode(HttpStatus.SC_OK)
                .body("$", hasKey("message"))
                .body("$", hasKey("valid"))
                .body("valid", is(false))
                .body("message", is("Json Comparison with id 11 already exist!"));
    }

    @ParameterizedTest
    @MethodSource("compartionsParams")
    @DisplayName("should save left and right comparision")
    public void shouldSaveLeftAndRightComparison(int id, String content, String resource) {

        this.repository.deleteAll();

        Response response = given().log().all()
                                .body(content)
                                .post(new StringBuilder("/v1/diff/").append(id).append("/").append(resource).toString());

        response.then().log().all()
                .statusCode(HttpStatus.SC_OK)
                .body("$", hasKey("content"))
                .body("$", hasKey("valid"))
                .body("valid", is(true))
                .body("content.id", is(id));

    }

    public static Stream<Arguments> compartionsParams() {
        return Stream.of(
                Arguments.of(10,"eyduYW1lJzonZmVsaXBlIG1vbnRlaXJvJ30=","left"),
                Arguments.of(10,"eyJuYW1lIjogImZlbGlwZSBtb250ZWlybyJ9","right")
        );
    }
}