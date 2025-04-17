package utc.cinemas.service.room;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utc.cinemas.model.dto.Response;
import utc.cinemas.model.dto.ResponseCode;
import utc.cinemas.model.dto.RoomDto;
import utc.cinemas.model.entity.Room;
import utc.cinemas.repository.RoomRepository;
import utc.cinemas.util.DatabaseUtils;
import utc.cinemas.util.JsonUtils;
import utc.cinemas.util.Utils;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public Response getListOfRooms(Map<String, String> filters) {
        try {
            String search = JsonUtils.convert(filters.get("search"), String.class).trim();
            Long cinemaId = JsonUtils.convert(filters.get("cinemaId"), Long.class);
            Map<String, Object> result = DatabaseUtils.getList(filters, pageable -> roomRepository.findAll("%" + search + "%", cinemaId, pageable));
            return Utils.createResponse(ResponseCode.SUCCESS, result);
        } catch (Exception e) {
            log.error("Error fetching rooms: {}", e.getMessage());
            return Utils.createResponse(ResponseCode.ERROR);
        }
    }

    @Override
    public Response create(RoomDto roomDto) {
        try {
            Room room = roomDto.getEntity();
            DatabaseUtils.createEntity(room, roomRepository);
            return Utils.createResponse(ResponseCode.SUCCESS, "Thêm phòng chiếu mới thành công");
        } catch (Exception e) {
            log.error("Error adding room: {}", e.getMessage());
            return Utils.createResponse(ResponseCode.ERROR, "Thêm phòng chiếu mới thất bại");
        }
    }

    @Override
    public Response getRoomById(Long id) {
        try {
            Room room = roomRepository.findById(id).orElse(null);
            return Utils.createResponse(ResponseCode.SUCCESS, room);
        } catch (Exception e) {
            log.error("Error getting room: {}", e.getMessage());
            return Utils.createResponse(ResponseCode.ERROR, "Không thể tải thông tin phòng chiếu");
        }
    }

    @Override
    public Response update(RoomDto roomDto) {
        try {
            Room room = roomDto.getEntity();
            DatabaseUtils.updateEntity(room, roomRepository);
            return Utils.createResponse(ResponseCode.SUCCESS, "Cập nhật phòng chiếu thành công");
        } catch (Exception e) {
            log.error("Error editing room: {}", e.getMessage());
            return Utils.createResponse(ResponseCode.ERROR, "Cập nhật phòng chiếu thất bại");
        }
    }

    @Override
    public Response getAll() {
        try {
            List<Room> rooms = roomRepository.findAll();
            return Utils.createResponse(ResponseCode.SUCCESS, rooms);
        } catch (Exception e) {
            log.error("Error fetching rooms all: {}", e.getMessage());
            return Utils.createResponse(ResponseCode.ERROR);
        }
    }
}
