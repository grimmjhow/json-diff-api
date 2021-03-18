package br.com.waes.json.diff.dto;

import br.com.waes.json.diff.model.ComparisonStatus;
import br.com.waes.json.diff.model.JsonComparison;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JsonComparisonReportDto {

    private ComparisonStatus status;
    private JsonComparison left;
    private JsonComparison right;
}
