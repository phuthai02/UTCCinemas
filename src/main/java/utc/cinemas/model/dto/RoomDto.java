package utc.cinemas.model.dto;

import lombok.Data;
import org.springframework.beans.BeanUtils;
import utc.cinemas.model.entity.Room;

import java.sql.Timestamp;

@Data
public class RoomDto {
    private Long id;
    private Long cinemaId;
    private String name;
    private Integer seatCapacity;
    private Long createdUser;
    private Long modifiedUser;
    private Timestamp createdDate;
    private Timestamp modifiedDate;
    private Integer status;

    public Room getEntity() {
        Room room = new Room();
        BeanUtils.copyProperties(this, room);
        return room;
    }
}
