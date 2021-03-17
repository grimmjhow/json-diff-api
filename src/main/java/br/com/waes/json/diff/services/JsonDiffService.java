package br.com.waes.json.diff.services;

import br.com.waes.json.diff.model.JsonComparison;
import com.google.gson.JsonElement;
import org.springframework.data.util.Pair;

import javax.persistence.Tuple;

public interface JsonDiffService {
    JsonComparison saveIfNotExists(JsonComparison jsonComparison);
    JsonElement validateJsonComparison(JsonComparison jsonComparison);
    Pair<JsonComparison, JsonComparison> getLeftAndRightSide(long id);
}
