package utc.cinemas.model.dto;

import lombok.Data;

@Data
public class ReportRequest {
    private String reportType;
    private String dateRange;
    private Long entityId;
}
