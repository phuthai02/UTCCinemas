package utc.cinemas.model.dto.report;

import lombok.Data;

@Data
public class ReportRequest {
    private String reportType; // cinema, room, movie
    private String dateRange; // today, last7days, last30days, alltime
    private Long entityId; // ID của chi nhánh/phòng/phim (-1 = tất cả)
}
