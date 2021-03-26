package br.com.waes.json.diff.usecase;

import br.com.waes.json.diff.dto.JsonComparisonReportDto;

/**
 * Interface to implement Report Differences on Json structures
 * with inversion of control
 */
public interface JsonDiffReport {

    JsonComparisonReportDto executeJsonComparison(long id);
}
