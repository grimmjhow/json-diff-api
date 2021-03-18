package br.com.waes.json.diff.services;

import br.com.waes.json.diff.exceptions.BusinessException;
import br.com.waes.json.diff.model.JsonComparison;
import br.com.waes.json.diff.repositories.JsonComparisonRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class JsonDiffServiceImpl implements JsonDiffService {

    private final JsonComparisonRepository repository;
    private final Base64Service base64Service;
    private final ObjectMapper mapper;
    private final Gson gson = new Gson();

    public JsonComparison saveIfNotExists(JsonComparison jsonComparison) {
        log.info("method=saveIfNotExists jsonComparison={}", jsonComparison);

        JsonElement jsonElement = this.validateJsonComparison(jsonComparison);
        jsonComparison.setJson(jsonElement);

        return this.repository.save(jsonComparison);
    }

    @Override
    public JsonElement validateJsonComparison(JsonComparison jsonComparison) {
        if(jsonComparison.isBase64Empty())
            throw new BusinessException("content base64 encode could not be empty");


        Optional<JsonComparison> response = this.repository.findByIdAndSide(jsonComparison.getId(), jsonComparison.getSide());

        if(response.isPresent())
            throw new BusinessException(new StringBuilder("Json Comparison with id ")
                    .append(jsonComparison.getId())
                    .append(" already exist!").toString());

        String content = this.base64Service.decode(jsonComparison.getBase64Encode());

        return this.gson.fromJson(content, JsonObject.class).getAsJsonObject();
    }

    @Override
    public Pair<JsonComparison, JsonComparison> getLeftAndRightSide(long id) {
        List<JsonComparison> comparisons = this.repository.findByIdOrderBySide(id);

        if(comparisons.isEmpty() || comparisons.size() < 1)
            throw new BusinessException("Both sides of json comparison with id " + id + " was not found");

        return Pair.of(comparisons.get(0),comparisons.get(1));
    }
}