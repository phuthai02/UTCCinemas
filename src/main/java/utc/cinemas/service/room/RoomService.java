package utc.cinemas.service.room;

import utc.cinemas.model.dto.Response;
import utc.cinemas.model.dto.RoomDto;

import java.util.Map;

public interface RoomService {
    Response getListOfRooms(Map<String, String> filters);
    Response getRoomById(Long id);
    Response getAll();
    Response create(RoomDto roomDto);
    Response update(RoomDto roomDto);
    Response delete(Long id);
    Response toggleStatus(Long id);
}
