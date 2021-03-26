package br.com.waes.json.diff.usecase;

import br.com.waes.json.diff.dto.JsonComparisonDto;

/**
 * Interface to implement SaveJsonComparison use cases
 * with inversion of control concept
 */
public interface SaveJsonComparison {

    JsonComparisonDto saveDiff(JsonComparisonDto comparisonDto);
}
