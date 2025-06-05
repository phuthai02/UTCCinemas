package utc.cinemas.service.seat;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import utc.cinemas.model.dto.Response;
import utc.cinemas.model.dto.ResponseCode;
import utc.cinemas.model.dto.SeatDto;
import utc.cinemas.model.entity.Seat;
import utc.cinemas.repository.SeatRepository;
import utc.cinemas.util.DatabaseUtils;
import utc.cinemas.util.JsonUtils;
import utc.cinemas.util.Utils;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class SeatServiceImpl implements SeatService {

    @Autowired
    private SeatRepository seatRepository;

    @Override
    public Response getListOfSeats(@RequestParam Map<String, String> filters) {
        try {
            String search = Utils.getSearch(filters);
            Long cinemaId = JsonUtils.convert(filters.get("cinemaId"), Long.class);
            Long roomId = JsonUtils.convert(filters.get("roomId"), Long.class);
            Map<String, Object> result = DatabaseUtils.getList(filters, pageable -> seatRepository.findAll(search, cinemaId, roomId, pageable));
            return Utils.createResponse(ResponseCode.SUCCESS, result);
        } catch (Exception e) {
            log.error("Error fetching seats: {}", e.getMessage());
            return Utils.createResponse(ResponseCode.ERROR);
        }
    }

    @Override
    public Response create(SeatDto seatDto) {
        try {
            Seat seat = seatDto.getEntity();
            DatabaseUtils.createEntity(seat, seatRepository);
            return Utils.createResponse(ResponseCode.SUCCESS, "Thêm ghế ngồi mới thành công");
        } catch (Exception e) {
            log.error("Error adding seat: {}", e.getMessage());
            return Utils.createResponse(ResponseCode.ERROR, "Thêm ghế ngồi mới thất bại");
        }
    }

    @Override
    public Response getSeatById(Long id) {
        try {
            Seat seat = seatRepository.findById(id).orElse(null);
            return Utils.createResponse(ResponseCode.SUCCESS, seat);
        } catch (Exception e) {
            log.error("Error getting seat: {}", e.getMessage());
            return Utils.createResponse(ResponseCode.ERROR, "Không thể tải thông tin ghế ngồi");
        }
    }

    @Override
    public Response getAll(Long id) {
        try {
            List<Seat> seats = seatRepository.findAll();
            return Utils.createResponse(ResponseCode.SUCCESS, seats);
        } catch (Exception e) {
            log.error("Error fetching seats all: {}", e.getMessage());
            return Utils.createResponse(ResponseCode.ERROR);
        }
    }

    @Override
    public Response update(SeatDto seatDto) {
        try {
            Seat seat = seatDto.getEntity();
            DatabaseUtils.updateEntity(seat, seatRepository);
            return Utils.createResponse(ResponseCode.SUCCESS, "Cập nhật ghế ngồi thành công");
        } catch (Exception e) {
            log.error("Error editing seat: {}", e.getMessage());
            return Utils.createResponse(ResponseCode.ERROR, "Cập nhật ghế ngồi thất bại");
        }
    }

    @Override
    public Response toggleStatus(Long id) {
        try {
            DatabaseUtils.toggleStatus(id, seatRepository);
            return Utils.createResponse(ResponseCode.SUCCESS);
        } catch (EntityNotFoundException e) {
            log.error("Seat not found for ID {}: {}", id, e.getMessage());
            return Utils.createResponse(ResponseCode.ERROR, "Không thể tải thông tin ghế ngồi");
        } catch (Exception e) {
            log.error("Error toggling status seat: {}", e.getMessage());
            return Utils.createResponse(ResponseCode.ERROR);
        }
    }

    @Override
    public Response delete(Long id) {
        try {
            DatabaseUtils.deleteEntity(id, seatRepository);
            return Utils.createResponse(ResponseCode.SUCCESS);
        } catch (EntityNotFoundException e) {
            log.error("Seat not found for ID {}: {}", id, e.getMessage());
            return Utils.createResponse(ResponseCode.ERROR, "Không thể tải thông tin ghế ngồi");
        } catch (Exception e) {
            log.error("Error deleting seat: {}", e.getMessage());
            return Utils.createResponse(ResponseCode.ERROR);
        }
    }
}
