package utc.cinemas.service.cinema;

import utc.cinemas.model.dto.CinemaDto;
import utc.cinemas.model.dto.Response;

import java.util.Map;

public interface CinemaService {
    Response getListOfCinemas(Map<String, String> filters);
    Response getCinemaById(Long id);
    Response getAll();
    Response create(CinemaDto cinemaDto);
    Response update(CinemaDto cinemaDto);
    Response delete(Long id);
    Response toggleStatus(Long id);
}
