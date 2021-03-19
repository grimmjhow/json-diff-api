package br.com.waes.json.diff.usecase;

import br.com.waes.json.diff.dto.JsonComparisonReportDto;
import br.com.waes.json.diff.exceptions.BusinessException;
import br.com.waes.json.diff.model.JsonComparison;
import br.com.waes.json.diff.services.JsonDiffService;
import br.com.waes.json.diff.validator.JsonValidator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.diff.JsonDiff;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import static br.com.waes.json.diff.model.ComparisonStatus.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class JsonDiffReportImpl implements JsonDiffReport {

    private final JsonDiffService service;
    private final JsonValidator validator;

    @Override
    public JsonComparisonReportDto executeJsonComparison(long id) {
        Pair<JsonComparison, JsonComparison> leftAndRightSide = this.service.getLeftAndRightSide(id);

        JsonComparison left = leftAndRightSide.getFirst();
        JsonComparison right = leftAndRightSide.getSecond();

        return getJsonComparisonReportDto(left, right);
    }

    public JsonComparisonReportDto getJsonComparisonReportDto(JsonComparison left, JsonComparison right) {
        JsonObject leftObject = this.service.convertToJsonObject(left.getContent());
        JsonObject rightObject = this.service.convertToJsonObject(right.getContent());

        if(validator.isNotSameSize(leftObject,rightObject))
            return JsonComparisonReportDto.builder()
                    .status(DIFFERENT_SIZE)
                    .left(left)
                    .right(right).build();
        else if(validator.isNotSameContent(leftObject,rightObject))
            return JsonComparisonReportDto.builder()
                    .status(DIFFERENT_CONTENT)
                    .left(left)
                    .right(right)
                    .patch(diff(left,right))
                    .build();
        else
            return JsonComparisonReportDto.builder()
                    .status(EQUAL)
                    .left(left)
                    .right(right).build();
    }

    private JsonPatch diff(JsonComparison left, JsonComparison right) {
        try {

            ObjectMapper mapper = new ObjectMapper();
            JsonNode origin = mapper.readValue(left.getContent(), JsonNode.class);
            JsonNode source = mapper.readValue(right.getContent(), JsonNode.class);

            return JsonDiff.asJsonPatch(source,origin);
        }catch(Exception ex) {
            throw new BusinessException(ex.getMessage());
        }
    }

}
