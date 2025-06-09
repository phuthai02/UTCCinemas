package utc.cinemas.service.report;

import utc.cinemas.model.dto.Response;
import utc.cinemas.model.dto.report.ReportRequest;
import utc.cinemas.model.dto.report.ReportResponse;

public interface ReportService {
    ReportResponse generateReport(ReportRequest request);
}
