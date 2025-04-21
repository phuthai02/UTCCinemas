package utc.cinemas.model.dto;

import lombok.Data;
import org.springframework.beans.BeanUtils;
import utc.cinemas.model.entity.Showtime;

import java.sql.Timestamp;

@Data
public class ShowtimeDto {
    private Long id;
    private Long movieId;
    private Long roomId;
    private Timestamp startTime;
    private Long price;
    private Long createdUser;
    private Long modifiedUser;
    private Timestamp createdDate;
    private Timestamp modifiedDate;
    private Integer status;

    public Showtime getEntity() {
        Showtime showtime = new Showtime();
        BeanUtils.copyProperties(this, showtime);
        return showtime;
    }
}
