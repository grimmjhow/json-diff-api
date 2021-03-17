package br.com.waes.json.diff.mapper;

import br.com.waes.json.diff.dto.JsonComparisonDto;
import br.com.waes.json.diff.model.JsonComparison;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface JsonComparisonMapper {

    JsonComparisonMapper JSON_COMPARISON_MAPPER = Mappers.getMapper(JsonComparisonMapper.class);

    JsonComparison toJsonComparison(JsonComparisonDto dto);
}
