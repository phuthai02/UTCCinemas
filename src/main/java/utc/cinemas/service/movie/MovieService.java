package utc.cinemas.service.movie;

import utc.cinemas.model.dto.MovieDto;
import utc.cinemas.model.dto.Response;

import java.util.Map;

public interface MovieService {
    Response getListOfMovies(Map<String, String> filters);
    Response getMovieById(Long id);
    Response getAll();
    Response create(MovieDto movieDto);
    Response update(MovieDto movieDto);
    Response delete(Long id);
    Response toggleStatus(Long id);
}
