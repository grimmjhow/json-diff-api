package br.com.waes.json.diff.usecase;

import br.com.waes.json.diff.dto.JsonComparisonReportDto;
import br.com.waes.json.diff.model.JsonComparison;
import br.com.waes.json.diff.services.JsonDiffService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

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

        if(left.iSameContent(right))
            return JsonComparisonReportDto.builder().build(); //Define where's the difference
        else if(left.isSameSize(right) && !left.iSameContent(right))
            return null;
        else
            return null;

        return null;
    }
}