package br.com.waes.json.diff.services;

import br.com.waes.json.diff.model.JsonComparison;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.springframework.data.util.Pair;

/**
 * Interface to implement Json structure CURD operations
 * with inversion of control
 */
public interface JsonDiffService {


    JsonComparison saveIfNotExists(JsonComparison jsonComparison);
    JsonElement validateJsonComparison(JsonComparison jsonComparison);
    Pair<JsonComparison, JsonComparison> getLeftAndRightSide(long id);
    JsonObject convertToJsonObject(String content);
}
