package br.com.waes.json.diff.mapper;

import br.com.waes.json.diff.dto.JsonComparisonDto;
import br.com.waes.json.diff.model.JsonComparison;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Mapper class to convert
 * {@link JsonComparisonDto} to any other class
 */
@Mapper
public interface JsonComparisonMapper {

    JsonComparisonMapper JSON_COMPARISON_MAPPER = Mappers.getMapper(JsonComparisonMapper.class);

    /**
     * Convert {@link JsonComparisonDto} to {@link JsonComparison}
     * @param dto {@link JsonComparisonDto} instance
     * @return JsonComparison instance with all values copied from {@link JsonComparisonDto}
     */
    JsonComparison toJsonComparison(JsonComparisonDto dto);
}
