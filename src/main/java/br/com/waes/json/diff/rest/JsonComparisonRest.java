package br.com.waes.json.diff.rest;

import br.com.waes.json.diff.dto.JsonComparisonDto;
import br.com.waes.json.diff.dto.JsonComparisonReportDto;
import br.com.waes.json.diff.model.DiffSide;
import br.com.waes.json.diff.model.JsonComparison;
import br.com.waes.json.diff.repositories.JsonComparisonRepository;
import br.com.waes.json.diff.usecase.JsonDiffReport;
import br.com.waes.json.diff.usecase.SaveJsonComparison;
import br.com.waes.json.diff.web.utils.DefaultResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/diff")
public class JsonComparisonRest {

    private final SaveJsonComparison saveJsonComparison;
    private final JsonDiffReport report;

    //TODO don't forget to remove!
    private final JsonComparisonRepository repository;

    @PostMapping("/{id}/left")
    public DefaultResponse<JsonComparisonDto> setLetSide(@PathVariable("id") long id, @RequestBody(required = false) String content) {
        log.info("method=setLetDiff - id={} content={}",id, content);

        JsonComparisonDto jsonComparison = JsonComparisonDto.builder()
                                                .id(id)
                                                .base64Encode(content)
                                                .side(DiffSide.LEFT).build();
        log.info("method=setLeftDiff - jsonComparisonDto={}",jsonComparison);

        this.saveJsonComparison.saveDiff(jsonComparison);

        return DefaultResponse.validResponse(jsonComparison);
    }

    @PostMapping("/{id}/right")
    public DefaultResponse<JsonComparisonDto> setRightSide(@PathVariable("id") long id, @RequestBody(required = false) String content) {
        log.info("method=setLetDiff - id={} content={}",id, content);

        JsonComparisonDto jsonComparison = JsonComparisonDto.builder()
                .id(id)
                .content(content)
                .side(DiffSide.RIGHT).build();
        log.info("method=setLeftDiff - jsonComparisonDto={}",jsonComparison);

        this.saveJsonComparison.saveDiff(jsonComparison);

        return DefaultResponse.validResponse(jsonComparison);
    }

    @GetMapping("/{id}")
    public DefaultResponse<JsonComparisonReportDto> diffJsonFiles(@PathVariable("id") long id) {
        log.info("method=diffJsonFiles - id={}",id);

        JsonComparisonReportDto report = this.report.executeJsonComparison(id);
        log.info("method=diffJsonFiles - report={}",report);

        return DefaultResponse.validResponse(report);
    }

    @GetMapping()
    public DefaultResponse<List<JsonComparison>> getAllDiffs() {
        return DefaultResponse.validResponse(this.repository.findAll());
    }
}
