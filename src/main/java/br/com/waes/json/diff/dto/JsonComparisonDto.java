package br.com.waes.json.diff.dto;

import br.com.waes.json.diff.model.DiffSide;
import lombok.Builder;
import lombok.Data;

import static br.com.waes.json.diff.model.DiffSide.*;

@Data
@Builder
public class JsonComparisonDto {

    private long id;
    @Builder.Default
    private DiffSide side = NONE;
    @Builder.Default
    private String content = "";
}
