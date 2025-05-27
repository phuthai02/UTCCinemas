package utc.cinemas.service.seat;

import utc.cinemas.model.dto.Response;
import utc.cinemas.model.dto.SeatDto;
import utc.cinemas.model.entity.Seat;

import java.util.List;
import java.util.Map;

public interface SeatService {
    Response getListOfSeats(Map<String, String> filters);
    Response create(SeatDto seatDto);
    Response getSeatById(Long id);
    Response update(SeatDto seatDto);
    Response getSeatCountByRoomId(Long roomId);
    List<Seat> getSeatsByRoomId(Long roomId);
    void applyRoomStatusToSeats(Long roomId, Integer roomStatus);
}
