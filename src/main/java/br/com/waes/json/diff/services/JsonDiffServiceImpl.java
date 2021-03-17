package br.com.waes.json.diff.services;

import br.com.waes.json.diff.exceptions.BusinessException;
import br.com.waes.json.diff.model.DiffSide;
import br.com.waes.json.diff.model.JsonComparison;
import br.com.waes.json.diff.repositories.JsonComparisonRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.handler.codec.base64.Base64Encoder;
import io.netty.handler.codec.json.JsonObjectDecoder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static br.com.waes.json.diff.model.DiffSide.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class JsonDiffServiceImpl implements JsonDiffService {

    private final JsonComparisonRepository repository;
    private final Base64Service base64Service;
    private final ObjectMapper mapper;

    public JsonComparison saveIfNotExists(JsonComparison jsonComparison) {
        log.info("method=saveIfNotExists jsonComparison={}", jsonComparison);

        this.validateJsonComparison(jsonComparison);

        return this.repository.save(jsonComparison);
    }

    @Override
    public void validateJsonComparison(JsonComparison jsonComparison) {
        if(jsonComparison.isContentEmpty())
            throw new BusinessException("content base64 encode could not be empty");

        String content = this.base64Service.decode(jsonComparison.getContent());

        Optional<JsonComparison> response = this.repository.findByIdAndSide(jsonComparison.getId(), jsonComparison.getSide());

        if(response.isPresent())
            throw new BusinessException(new StringBuilder("Json Comparison with id ")
                    .append(jsonComparison.getId())
                    .append(" already exist!").toString());
    }

    @Override
    public Pair<JsonComparison, JsonComparison> getLeftAndRightSide(long id) {
        List<JsonComparison> comparisons = this.repository.findByIdOrderBySide(id);

        if(comparisons.isEmpty() || comparisons.size() < 1)
            throw new BusinessException("Both sides of json comparison with id " + id + " was not found");

//        JsonComparison left = this.repository.findByIdAndSide(id, LEFT)
//                                                    .orElseThrow(() -> new BusinessException("Left Json with id " + id + " was not found"));
//
//        JsonComparison right = this.repository.findByIdAndSide(id, RIGHT)
//                                                    .orElseThrow(() -> new BusinessException("Right Json with id " + id + " was not found"));
        return Pair.of(comparisons.get(0),comparisons.get(1));
    }
}