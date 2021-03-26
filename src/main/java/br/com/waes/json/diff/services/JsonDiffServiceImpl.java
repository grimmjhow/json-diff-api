package br.com.waes.json.diff.services;

import br.com.waes.json.diff.exceptions.BusinessException;
import br.com.waes.json.diff.model.JsonComparison;
import br.com.waes.json.diff.repositories.JsonComparisonRepository;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Specific implementation of {@link JsonDiffService}.
 * <p>
 *     Responsible for CRUD and validation operations of
 *     {@link JsonComparison} entity
 * </p>
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class JsonDiffServiceImpl implements JsonDiffService {

    private final JsonComparisonRepository repository;
    private final Base64Service base64Service;
    private final Gson gson;

    /**
     * Saves a {@link JsonComparison} or throw {@link BusinessException} when
     * already exist a {@link JsonComparison} with same ID and Side or base64 is null or empty.
     * @param jsonComparison Valid instance of {@link JsonComparison} with ID and base64
     * @return Saved {@link JsonComparison}
     * @exception {@link BusinessException} when already exist a {@link JsonComparison} with
     * same ID and Side.
     */
    public JsonComparison saveIfNotExists(JsonComparison jsonComparison) {
        log.info("method=saveIfNotExists jsonComparison={}", jsonComparison);

        JsonObject jsonObject = this.validateJsonComparison(jsonComparison);

        jsonComparison.setContent(jsonObject.toString());

        return this.repository.save(jsonComparison);
    }

    /**
     * Valid {@link JsonComparison} attributes
     * @param jsonComparison Valid {@link JsonComparison} with Id and base64
     * @return Validated base64 decoded as Json
     */
    @Override
    public JsonObject validateJsonComparison(JsonComparison jsonComparison) {
        if(jsonComparison.isBase64Empty())
            throw new BusinessException("content base64 encode could not be empty");


        Optional<JsonComparison> response = this.repository.findByIdAndSide(jsonComparison.getId(), jsonComparison.getSide());

        if(response.isPresent())
            throw new BusinessException(new StringBuilder("Json Comparison with id ")
                                                    .append(jsonComparison.getId())
                                                    .append(" already exist!").toString());

        String content = this.base64Service.decode(jsonComparison.getBase64Encode());

        return this.convertToJsonObject(content);
    }

    /**
     * Finds both sides of a comparison by a valid id or
     * throw exception if there's only one side saved
     * @param id Comparison ID
     * @return Pair structure with left and right comparison
     */
    @Override
    public Pair<JsonComparison, JsonComparison> getLeftAndRightSide(long id) {
        List<JsonComparison> comparisons = this.repository.findByIdOrderBySide(id);

        if(comparisons.size() <= 1)
            throw new BusinessException("Both sides of json comparison with id " + id + " was not found");

        return Pair.of(comparisons.get(0),comparisons.get(1));
    }

    /**
     * Converts valid {@link String} with json content into {@link JsonObject}
     * @param content {@link String} with valid json content
     * @return Converted {@link JsonObject} from String
     */
    public JsonObject convertToJsonObject(String content) {
        return this.gson.fromJson(content,JsonObject.class);
    }
}