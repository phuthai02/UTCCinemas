package utc.cinemas.service.showtime;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import utc.cinemas.model.dto.Response;
import utc.cinemas.model.dto.ResponseCode;
import utc.cinemas.model.dto.ShowtimeDto;
import utc.cinemas.model.entity.Seat;
import utc.cinemas.model.entity.Showtime;
import utc.cinemas.repository.ShowtimeRepository;
import utc.cinemas.service.seat.SeatService;
import utc.cinemas.service.ticket.TicketSerivce;
import utc.cinemas.util.DatabaseUtils;
import utc.cinemas.util.JsonUtils;
import utc.cinemas.util.Utils;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ShowtimeServiceImpl implements ShowtimeService {

    @Autowired
    private ShowtimeRepository showtimeRepository;

    @Autowired
    private SeatService seatService;

    @Autowired
    private TicketSerivce ticketSerivce;

    @Override
    public Response getListOfShowtimes(@RequestParam Map<String, String> filters) {
        try {
            String search = Utils.getSearch(filters);
            Long cinemaId = JsonUtils.convert(filters.get("cinemaId"), Long.class);
            Long roomId = JsonUtils.convert(filters.get("roomId"), Long.class);
            Long movieId = JsonUtils.convert(filters.get("movieId"), Long.class);
            Map<String, Object> result = DatabaseUtils.getList(filters, pageable ->
                    showtimeRepository.findAll(search, cinemaId, roomId, movieId, pageable));
            return Utils.createResponse(ResponseCode.SUCCESS, result);
        } catch (Exception e) {
            log.error("Error fetching showtimes: {}", e.getMessage());
            return Utils.createResponse(ResponseCode.ERROR);
        }
    }

    @Override
    public Response getAll() {
        try {
            List<Showtime> showtimes = showtimeRepository.findAll();
            return Utils.createResponse(ResponseCode.SUCCESS, showtimes);
        } catch (Exception e) {
            log.error("Error fetching all showtimes: {}", e.getMessage());
            return Utils.createResponse(ResponseCode.ERROR);
        }
    }

    @Override
    public Response create(ShowtimeDto showtimeDto) {
        try {
            Showtime showtime = showtimeDto.getEntity();
            DatabaseUtils.createEntity(showtime, showtimeRepository);

            List<Seat> seats = seatService.getSeatsByRoomId(showtime.getRoomId());
            if (seats.isEmpty()) return Utils.createResponse(ResponseCode.ERROR, "Phòng chiếu này chưa có ghế ngồi");

            ticketSerivce.createTicketBatch(showtime.getId(), seats);

            return Utils.createResponse(ResponseCode.SUCCESS, "Thêm suất chiếu mới thành công");
        } catch (Exception e) {
            log.error("Error adding showtime: {}", e.getMessage());
            return Utils.createResponse(ResponseCode.ERROR, "Thêm suất chiếu mới thất bại");
        }
    }

    @Override
    public Response getShowtimeById(Long id) {
        try {
            Showtime showtime = showtimeRepository.findById(id).orElse(null);
            return Utils.createResponse(ResponseCode.SUCCESS, showtime);
        } catch (Exception e) {
            log.error("Error getting showtime: {}", e.getMessage());
            return Utils.createResponse(ResponseCode.ERROR, "Không thể tải thông tin suất chiếu");
        }
    }

    @Override
    public Response update(ShowtimeDto showtimeDto) {
        try {
            Showtime showtime = showtimeDto.getEntity();
            DatabaseUtils.updateEntity(showtime, showtimeRepository);
            return Utils.createResponse(ResponseCode.SUCCESS, "Cập nhật suất chiếu thành công");
        } catch (Exception e) {
            log.error("Error editing showtime: {}", e.getMessage());
            return Utils.createResponse(ResponseCode.ERROR, "Cập nhật suất chiếu thất bại");
        }
    }

    @Override
    public Response toggleStatus(Long id) {
        try {
            DatabaseUtils.toggleStatus(id, showtimeRepository);
            return Utils.createResponse(ResponseCode.SUCCESS);
        } catch (EntityNotFoundException e) {
            log.error("Showtime not found for ID {}: {}", id, e.getMessage());
            return Utils.createResponse(ResponseCode.ERROR, "Không thể tải thông tin suất chiếu");
        } catch (Exception e) {
            log.error("Error toggling status showtime: {}", e.getMessage());
            return Utils.createResponse(ResponseCode.ERROR);
        }
    }

    @Override
    public Response delete(Long id) {
        try {
            DatabaseUtils.deleteEntity(id, showtimeRepository);
            return Utils.createResponse(ResponseCode.SUCCESS);
        } catch (EntityNotFoundException e) {
            log.error("Showtime not found for ID {}: {}", id, e.getMessage());
            return Utils.createResponse(ResponseCode.ERROR, "Không thể tải thông tin suất chiếu");
        } catch (Exception e) {
            log.error("Error deleting showtime: {}", e.getMessage());
            return Utils.createResponse(ResponseCode.ERROR);
        }
    }
}