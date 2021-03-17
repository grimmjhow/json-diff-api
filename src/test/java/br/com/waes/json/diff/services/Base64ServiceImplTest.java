package br.com.waes.json.diff.services;

import br.com.waes.json.diff.utils.BaseTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class Base64ServiceImplTest extends BaseTest {

    private Base64ServiceImpl service;

    @BeforeEach
    public void setup() {
        this.service = new Base64ServiceImpl();
    }

    @ParameterizedTest
    @MethodSource("base64Values")
    @DisplayName("should decode base64 to a readable String")
    public void decodeBase64ToString(String base64, String expected) throws JsonProcessingException {

        String actual = this.service.decode(base64);

        assertEquals(expected,actual);
    }

    public static Stream<Arguments> base64Values() {
        return Stream.of(
//                Arguments.of("eyJuYW1lIjoiZmVsaXBlIG1vbnRlaXJvIn0=","{\"name\":\"felipe monteiro\"}")
//                Arguments.of("eyAKIm5hbWUiOiAiRmVsaXBlIE1vbnRlaXJvIiwKImFkZHJlc3MiOiB7CiJjaXR5IjogIk1vZ2kgZGFzIENydXplcyIsCiJjb3VudHJ5IjogIlPDo28gUGF1bG8iCn0KfQ==","{ \n" + "\"name\": \"Felipe Monteiro\",\n" + "\"address\": {\n" + "\"city\": \"Mogi das Cruzes\",\n" + "\"country\": \"São Paulo\"\n" + "}\n" + "}"),
                Arguments.of("ImZlbGlwZSBtb250ZWlybyIgLSA/ICogfiBAIyEnJzEwMg==","\"felipe monteiro\" - ? * ~ @#!''102")
        );
    }
}