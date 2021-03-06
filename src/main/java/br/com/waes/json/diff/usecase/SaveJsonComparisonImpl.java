package br.com.waes.json.diff.usecase;

import br.com.waes.json.diff.dto.JsonComparisonDto;
import br.com.waes.json.diff.model.JsonComparison;
import br.com.waes.json.diff.services.JsonDiffService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static br.com.waes.json.diff.mapper.JsonComparisonMapper.*;

/**
 * Specific implementation of {@link SaveJsonComparison}
 * <p>
 *     Use case responsible to save left or right side of JsonComparison
 * </p>
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SaveJsonComparisonImpl implements SaveJsonComparison {

    private final JsonDiffService service;

    /**
     * Saves right or left side of a comparison
     * @param comparisonDto valid instance of {@link JsonComparisonDto} with id, base64 and side
     * @return saved {@link JsonComparisonDto}
     */
    @Override
    public JsonComparisonDto saveDiff(JsonComparisonDto comparisonDto) {
        log.info("method=save comparsionDto={}", comparisonDto);

        JsonComparison jsonComparison = JSON_COMPARISON_MAPPER.toJsonComparison(comparisonDto);
        log.info("method=saveDiff toJsonComparison={}",jsonComparison);

        JsonComparison savedComparison = this.service.saveIfNotExists(jsonComparison);
        log.info("method=saveDiff jsonComparison={}",savedComparison);

        return comparisonDto;
    }
}
