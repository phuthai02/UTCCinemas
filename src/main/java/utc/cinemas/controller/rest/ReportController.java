package utc.cinemas.controller.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utc.cinemas.model.dto.Response;
import utc.cinemas.model.dto.ResponseCode;
import utc.cinemas.model.dto.report.ReportRequest;
import utc.cinemas.model.dto.report.ReportResponse;
import utc.cinemas.service.report.ReportService;
import utc.cinemas.util.JsonUtils;
import utc.cinemas.util.Utils;

@RestController
@RequestMapping("/api/admin/reports")
@Slf4j
public class ReportController {

    @Autowired
    private ReportService reportService;

    @PostMapping("/generate")
    public ResponseEntity<Response> generateReport(@RequestBody ReportRequest request) {
        log.info("Generating report request: {}", request);
        try {
            ReportResponse response = reportService.generateReport(request);
            Response res = Utils.createResponse(ResponseCode.SUCCESS, response);
            return ResponseEntity.status(HttpStatus.OK).body(res);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

//    @PostMapping("export")
//    public ResponseEntity<Response> exportReport(@RequestBody ReportRequest request) {
//        log.info("Export report: params={}", JsonUtils.toString(request));
//        Response response = reportService.exportReport(request);
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }
}