package utc.cinemas.service.cinema;

import utc.cinemas.model.dto.CinemasDto;
import utc.cinemas.model.dto.Response;

import java.util.Map;

public interface CinemaService {
    Response getListOfCinemas(Map<String, String> filters);
    Response create(CinemasDto cinemasDto);
    Response getCinemaById(Long id);
    Response update(CinemasDto cinemasDto);
}
