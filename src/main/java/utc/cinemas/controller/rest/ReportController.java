package utc.cinemas.controller.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utc.cinemas.model.dto.ReportRequest;
import utc.cinemas.model.dto.Response;
import utc.cinemas.service.report.ReportService;
import utc.cinemas.util.JsonUtils;

@RestController
@RequestMapping("/api/admin/reports")
@Slf4j
public class ReportController {

    @Autowired
    private ReportService reportService;

    @PostMapping("generate")
    public ResponseEntity<Response> generateReport(@RequestBody ReportRequest request) {
        log.info("Generating report: params={}", JsonUtils.toString(request));
        Response response = reportService.generateReport(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}