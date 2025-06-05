package utc.cinemas.service.seat;

import utc.cinemas.model.dto.Response;
import utc.cinemas.model.dto.SeatDto;

import java.util.List;
import java.util.Map;

public interface SeatService {
    Response getListOfSeats(Map<String, String> filters);
    Response getSeatById(Long id);
    Response getAll(Long id);
    Response create(SeatDto seatDto);
    Response update(SeatDto seatDto);
    Response delete(Long id);
    Response toggleStatus(Long id);
}
