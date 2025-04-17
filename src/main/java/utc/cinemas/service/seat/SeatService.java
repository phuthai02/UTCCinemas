package utc.cinemas.service.seat;

import utc.cinemas.model.dto.Response;
import utc.cinemas.model.dto.SeatDto;

import java.util.Map;

public interface SeatService {
    Response getListOfSeats(Map<String, String> filters);
    Response create(SeatDto seatDto);
    Response getSeatById(Long id);
    Response update(SeatDto seatDto);
}
