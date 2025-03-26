package utc.cinemas.service.movie;

import utc.cinemas.model.dto.MoviesDto;
import utc.cinemas.model.dto.Response;
import utc.cinemas.model.entity.Movie;

import java.util.Map;

public interface MovieService {
    Response getListOfMovies(Map<String, String> filters);
    Response create(MoviesDto moviesDto);
}
