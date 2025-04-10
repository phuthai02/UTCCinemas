package utc.cinemas.service.movie;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import utc.cinemas.model.dto.MoviesDto;
import utc.cinemas.model.dto.Response;
import utc.cinemas.model.dto.ResponseCode;
import utc.cinemas.model.entity.Movie;
import utc.cinemas.repository.MovieRepository;
import utc.cinemas.util.DatabaseUtils;
import utc.cinemas.util.Utils;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieRepository movieRepository;

    @Override
    public Response getListOfMovies(Map<String, String> filters) {
        try {
            Integer page = Integer.parseInt(filters.getOrDefault("page", "0"));
            Integer pageSize = Integer.parseInt(filters.getOrDefault("pageSize", "20"));
            String sortBy = filters.getOrDefault("sortBy", "id");
            String sortOrder = filters.getOrDefault("sortOrder", "desc");
            String search = filters.getOrDefault("search", "");

            Sort.Direction direction = Sort.Direction.fromString(sortOrder);
            Pageable pageable = PageRequest.of(page, pageSize, Sort.by(direction, sortBy));
            Page<Movie> movies;

            movies = movieRepository.findAll("%" + search.trim() + "%", pageable);

            Map<String, Object> response = new HashMap<>();
            response.put("data", movies.getContent());
            response.put("totalPages", movies.getTotalPages());

            return Utils.createResponse(ResponseCode.SUCCESS, response);
        } catch (Exception e) {
            log.error("Error fetching movies: {}", e.getMessage());
            return Utils.createResponse(ResponseCode.ERROR);
        }
    }
//
//    @Override
//    public Response getMovieById(Long id) {
//        try {
//
//            return Utils.createResponse(ResponseCode.SUCCESS);
//        } catch (Exception e) {
//            log.error("Error fetching movie by ID: {}", e.getMessage());
//            return Utils.createResponse(ResponseCode.ERROR);
//        }
//    }

    @Override
    public Response create(MoviesDto moviesDto) {
        try {
            Movie movie = moviesDto.getEntity();
            DatabaseUtils.createEntity(movie, movieRepository);
            return Utils.createResponse(ResponseCode.SUCCESS, "Thêm phim mới thành công");
        } catch (DataIntegrityViolationException e) {
            if (e.getMessage().contains("Duplicate entry")) {
                return Utils.createResponse(ResponseCode.ERROR, "Tên phim đã tồn tại");
            }
            return Utils.createResponse(ResponseCode.ERROR);
        } catch (Exception e) {
            log.error("Error adding movie: {}", e.getMessage());
            return Utils.createResponse(ResponseCode.ERROR, "Thêm phim mới thất bại");
        }
    }


//    @Override
//    public Response updateMovie(Long id, Movie movieDetails) {
//        try {
//
//            return Utils.createResponse(ResponseCode.SUCCESS);
//        } catch (Exception e) {
//            log.error("Error updating movie: {}", e.getMessage());
//            return Utils.createResponse(ResponseCode.ERROR);
//        }
//    }

//    @Override
//    public Response deleteMovie(Long id) {
//        try {
//
//            return Utils.createResponse(ResponseCode.SUCCESS);
//        } catch (Exception e) {
//            log.error("Error deleting movie: {}", e.getMessage());
//            return Utils.createResponse(ResponseCode.ERROR);
//        }
//    }
}
