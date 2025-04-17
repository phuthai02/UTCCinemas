package utc.cinemas.service.room;

import utc.cinemas.model.dto.Response;
import utc.cinemas.model.dto.RoomDto;

import java.util.Map;

public interface RoomService {
    Response getListOfRooms(Map<String, String> filters);
    Response create(RoomDto roomDto);
    Response getRoomById(Long id);
    Response update(RoomDto roomDto);
    Response getAll();
}
