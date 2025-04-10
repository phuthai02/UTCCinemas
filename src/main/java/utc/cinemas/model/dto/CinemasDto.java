package utc.cinemas.model.dto;

import lombok.Data;
import org.springframework.beans.BeanUtils;
import utc.cinemas.model.entity.Cinema;

import java.sql.Timestamp;

@Data
public class CinemasDto {
    private Long id;
    private String name;
    private String location;
    private Long createdUser;
    private Long modifiedUser;
    private Timestamp createdDate;
    private Timestamp modifiedDate;
    private Integer status;

    public Cinema getEntity() {
        Cinema cinema = new Cinema();
        BeanUtils.copyProperties(this, cinema);
        return cinema;
    }
}
