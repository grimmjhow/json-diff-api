package br.com.waes.json.diff.usecase;

import br.com.waes.json.diff.dto.JsonComparisonReportDto;
import br.com.waes.json.diff.model.JsonComparison;
import br.com.waes.json.diff.services.JsonDiffService;
import br.com.waes.json.diff.services.JsonDiffServiceImpl;
import br.com.waes.json.diff.utils.BaseTest;
import br.com.waes.json.diff.validator.JsonValidator;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.data.util.Pair;

import java.util.UUID;
import java.util.stream.Stream;

import static br.com.waes.json.diff.model.ComparisonStatus.*;
import static br.com.waes.json.diff.model.DiffSide.LEFT;
import static br.com.waes.json.diff.model.DiffSide.RIGHT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

class JsonDiffReportImplTest extends BaseTest {

    private JsonDiffReportImpl diffReport;
    private JsonDiffService service;

    @BeforeEach
    public void setup() {
        this.service = spy(new JsonDiffServiceImpl(null,null,new Gson()));
        JsonValidator validator = new JsonValidator();
        this.diffReport = new JsonDiffReportImpl(service,validator);
    }

    @ParameterizedTest
    @MethodSource("getComparisonParameters")
    @DisplayName("should create json comparison report")
    public void shouldCreateJsonComparisonReport(int id, Pair<JsonComparison,JsonComparison> pair, JsonComparisonReportDto expected) {
        //setup spy
        doReturn(pair).when(this.service).getLeftAndRightSide(id);

        JsonComparisonReportDto actual = this.diffReport.executeJsonComparison(id);

        assertEquals(expected, actual);
    }

    public static Stream<Arguments> getComparisonParameters() {
        //Case --> Different Size
        JsonComparison left1 = new JsonComparison(UUID.randomUUID().toString(),10, LEFT,"","{\"name\": \"Felipe Monteiro\",\"age\": 29}");
        JsonComparison right1 = new JsonComparison(UUID.randomUUID().toString(),10, RIGHT,"","{\"name\": \"Felipe Monteiro\"}");
        JsonComparisonReportDto differentSize = JsonComparisonReportDto.builder().status(DIFFERENT_SIZE).left(left1).right(right1).build();

        //Case --> Same Size and Content
        JsonComparison left2 = new JsonComparison(UUID.randomUUID().toString(),11, LEFT,"","{\"name\": \"Felipe Monteiro\",\"age\": 29}");
        JsonComparison right2 = new JsonComparison(UUID.randomUUID().toString(),11, RIGHT,"","{\"name\": \"Felipe Monteiro\",\"age\": 29}");
        JsonComparisonReportDto equalSizeAndContent = JsonComparisonReportDto.builder().status(EQUAL).left(left2).right(right2).build();

        //Case --> Same Size but different content
        String contentLeft3 = "{\"name\": \"Felipe Monteiro\",\"age\": 29}";
        JsonComparison left3 = new JsonComparison(UUID.randomUUID().toString(),12, LEFT,"",contentLeft3);

        String contentRight3 = "{\"name\": \"Felipe Melo\",\"age\": 29}";
        JsonComparison right3 = new JsonComparison(UUID.randomUUID().toString(),12, LEFT,"",contentRight3);

        JsonComparisonReportDto sameSizeDifferentContent = JsonComparisonReportDto.builder().status(DIFFERENT_CONTENT).left(left3).right(right3).build();
        return Stream.of(
                Arguments.of(10,Pair.of(left1,right1),differentSize),
                Arguments.of(11,Pair.of(left2,right2),equalSizeAndContent),
                Arguments.of(12,Pair.of(left3,right3),sameSizeDifferentContent)
        );
    }

}