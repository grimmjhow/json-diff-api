package br.com.waes.json.diff.services;

import br.com.waes.json.diff.model.JsonComparison;
import org.springframework.data.util.Pair;

import javax.persistence.Tuple;

public interface JsonDiffService {
    JsonComparison saveIfNotExists(JsonComparison jsonComparison);
    void validateJsonComparison(JsonComparison jsonComparison);
    Pair<JsonComparison, JsonComparison> getLeftAndRightSide(long id);
}
