package utc.cinemas.service.report;

import utc.cinemas.model.dto.ReportRequest;
import utc.cinemas.model.dto.Response;

public interface ReportService {
    Response generateReport(ReportRequest request);
}
