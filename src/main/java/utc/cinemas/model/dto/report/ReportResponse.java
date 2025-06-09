package utc.cinemas.model.dto.report;

import lombok.Data;

import java.util.List;

@Data
public class ReportResponse {
    private Long totalTickets;
    private Long totalRevenue;
    private Long totalViews;
    private Long totalShowtimes;
    private ChartData chartData;
    private List<TableData> tableData;
}