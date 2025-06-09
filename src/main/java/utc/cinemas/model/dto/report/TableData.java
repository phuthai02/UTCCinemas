package utc.cinemas.model.dto.report;

import lombok.Data;

@Data
public class TableData {
    private String name;
    private Long ticketCount;
    private Long revenue;
    private Double percentage;
}
