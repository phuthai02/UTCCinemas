package utc.cinemas.service.movie;

import utc.cinemas.model.dto.MovieDto;
import utc.cinemas.model.dto.Response;

import java.util.Map;

public interface MovieService {
    Response getListOfMovies(Map<String, String> filters);
    Response create(MovieDto movieDto);
}
