package utc.cinemas.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketDto {
    private String movie;
    private String cinema;
    private String room;
    private String seat;
    private String username;
    private Timestamp createdDate;
    private Long createdUser;
    private Integer status;
}
