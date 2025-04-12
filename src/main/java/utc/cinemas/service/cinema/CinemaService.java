package utc.cinemas.service.cinema;

import utc.cinemas.model.dto.CinemaDto;
import utc.cinemas.model.dto.Response;

import java.util.Map;

public interface CinemaService {
    Response getListOfCinemas(Map<String, String> filters);
    Response create(CinemaDto cinemaDto);
    Response getCinemaById(Long id);
    Response update(CinemaDto cinemaDto);
    Response getAll();
}
