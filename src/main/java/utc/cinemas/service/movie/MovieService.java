package utc.cinemas.service.movie;

import utc.cinemas.model.dto.MovieDto;
import utc.cinemas.model.dto.Response;

import java.util.Map;

public interface MovieService {
    Response getListOfMovies(Map<String, String> filters);
    Response create(MovieDto movieDto);
    Response getMovieById(Long id);
    Response update(MovieDto movieDto);
    Response getAll();
    Response toggleStatus(Long id);
    Response deletePermission(Long id);
}
