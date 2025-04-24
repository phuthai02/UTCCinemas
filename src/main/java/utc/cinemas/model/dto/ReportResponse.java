package utc.cinemas.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReportResponse {
    private Long totalTickets;
    private Long totalRevenue;
    private Long totalViews;
    private Long totalShowtimes;

    public ReportResponse(Long totalTickets, Long totalRevenue, Long totalViews, Long totalShowtimes) {
        this.totalTickets = totalTickets;
        this.totalRevenue = totalRevenue;
        this.totalViews = totalViews;
        this.totalShowtimes = totalShowtimes;
    }
}
