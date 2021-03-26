package br.com.waes.json.diff.rest;

import br.com.waes.json.diff.dto.JsonComparisonDto;
import br.com.waes.json.diff.dto.JsonComparisonReportDto;
import br.com.waes.json.diff.model.DiffSide;
import br.com.waes.json.diff.usecase.JsonDiffReport;
import br.com.waes.json.diff.usecase.SaveJsonComparison;
import br.com.waes.json.diff.web.utils.DefaultResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * Rest API service to handle
 * json structure CRUD and Diff operations
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/diff")
public class JsonComparisonRest {

    private final SaveJsonComparison saveJsonComparison;
    private final JsonDiffReport report;

    /**
     * Saves base64 content and associate with left side comparison
     * @param id Comparison ID (needs to be unique)
     * @param content base64 content
     * @return A saved {@link JsonComparisonDto} with base64, converted content and unique ID
     */
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

    /**
     * Saves base64 content and associate with right side comparison
     * @param id Comparison ID (needs to be unique)
     * @param content base64 content
     * @return A saved {@link JsonComparisonDto} with base64, converted content and unique ID
     */
    @PostMapping("/{id}/right")
    public DefaultResponse<JsonComparisonDto> setRightSide(@PathVariable("id") long id, @RequestBody(required = false) String content) {
        log.info("method=setLetDiff - id={} content={}",id, content);

        JsonComparisonDto jsonComparison = JsonComparisonDto.builder()
                .id(id)
                .base64Encode(content)
                .side(DiffSide.RIGHT).build();
        log.info("method=setLeftDiff - jsonComparisonDto={}",jsonComparison);

        this.saveJsonComparison.saveDiff(jsonComparison);

        return DefaultResponse.validResponse(jsonComparison);
    }

    /**
     * Create a report to explain all differences between left side and right side
     * @param id Comparison id (same used to create left and right side comparison)
     * @return A {@link JsonComparisonReportDto} with all differences
     */
    @GetMapping("/{id}")
    public DefaultResponse<JsonComparisonReportDto> diffJsonFiles(@PathVariable("id") long id) {
        log.info("method=diffJsonFiles - id={}",id);

        JsonComparisonReportDto report = this.report.executeJsonComparison(id);
        log.info("method=diffJsonFiles - report={}",report);

        return DefaultResponse.validResponse(report);
    }
}
