package br.com.waes.json.diff.usecase;

import br.com.waes.json.diff.dto.JsonComparisonReportDto;
import br.com.waes.json.diff.model.JsonComparison;
import br.com.waes.json.diff.services.JsonDiffService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import static br.com.waes.json.diff.dto.JsonComparisonReportDto.*;
import static br.com.waes.json.diff.model.ComparisonStatus.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class JsonDiffReportImpl implements JsonDiffReport {

    private final JsonDiffService service;

    @Override
    public JsonComparisonReportDto executeJsonComparison(long id) {
        Pair<JsonComparison, JsonComparison> leftAndRightSide = this.service.getLeftAndRightSide(id);

        JsonComparison left = leftAndRightSide.getFirst();
        JsonComparison right = leftAndRightSide.getSecond();

        if(!left.isSameSize(right))
            return builder()
                    .status(EQUAL)
                    .left(left)
                    .right(right).build();
        else if(!left.isSameContent(right))
            return builder()
                    .status(DIFFERENT_SIZE)
                    .left(left)
                    .right(right).build();
        else
            return builder()
                    .status(DIFFERENT_CONTENT)
                    .left(left)
                    .right(right).build();
    }
}
