package br.com.waes.json.diff.mapper;

import br.com.waes.json.diff.dto.JsonComparisonDto;
import br.com.waes.json.diff.model.JsonComparison;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-03-16T22:20:41-0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 15.0.1 (Oracle Corporation)"
)
public class JsonComparisonMapperImpl implements JsonComparisonMapper {

    @Override
    public JsonComparison toJsonComparison(JsonComparisonDto dto) {
        if ( dto == null ) {
            return null;
        }

        JsonComparison jsonComparison = new JsonComparison();

        jsonComparison.setId( dto.getId() );
        jsonComparison.setSide( dto.getSide() );
        jsonComparison.setContent( dto.getContent() );

        return jsonComparison;
    }
}
