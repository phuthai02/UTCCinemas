package utc.cinemas.model.dto;

import lombok.Data;
import org.springframework.beans.BeanUtils;
import utc.cinemas.model.entity.Seat;

import java.sql.Timestamp;

@Data
public class SeatDto {
    private Long id;
    private Long roomId;
    private String seatNumber;
    private Long createdUser;
    private Long modifiedUser;
    private Timestamp createdDate;
    private Timestamp modifiedDate;
    private Integer status;

    public Seat getEntity() {
        Seat seat = new Seat();
        BeanUtils.copyProperties(this, seat);
        return seat;
    }
}
