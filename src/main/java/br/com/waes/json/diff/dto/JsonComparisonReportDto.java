package br.com.waes.json.diff.dto;

import br.com.waes.json.diff.model.ComparisonStatus;
import br.com.waes.json.diff.model.JsonComparison;
import com.github.fge.jsonpatch.JsonPatch;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
public class JsonComparisonReportDto {

    private ComparisonStatus status;
    private JsonComparison left;
    private JsonComparison right;
    @EqualsAndHashCode.Exclude
    private JsonPatch patch;
}
