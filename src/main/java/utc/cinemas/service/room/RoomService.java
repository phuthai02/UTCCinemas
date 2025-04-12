package utc.cinemas.service.room;

import utc.cinemas.model.dto.Response;

import java.util.Map;

public interface RoomService {
    Response getListOfRooms(Map<String, String> filters);
    Response create(RoomDto roomDto);
    Response getRoomById(Long id);
    Response update(RoomDto roomDto);
}
