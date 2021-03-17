package br.com.waes.json.diff.usecase;

import br.com.waes.json.diff.dto.JsonComparisonDto;

public interface SaveJsonComparison {

    JsonComparisonDto saveDiff(JsonComparisonDto comparisonDto);
}
