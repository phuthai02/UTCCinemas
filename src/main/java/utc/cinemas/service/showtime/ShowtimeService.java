package utc.cinemas.service.showtime;

import utc.cinemas.model.dto.Response;
import utc.cinemas.model.dto.ShowtimeDto;

import java.util.Map;

public interface ShowtimeService {
    Response getListOfShowtimes(Map<String, String> filters);
    Response getAll();
    Response getShowtimeById(Long id);
    Response create(ShowtimeDto showtimeDto);
    Response update(ShowtimeDto showtimeDto);
}
