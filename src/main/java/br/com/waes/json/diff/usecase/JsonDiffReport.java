package br.com.waes.json.diff.usecase;

import br.com.waes.json.diff.dto.JsonComparisonReportDto;

public interface JsonDiffReport {

    JsonComparisonReportDto executeJsonComparison(long id);
}
