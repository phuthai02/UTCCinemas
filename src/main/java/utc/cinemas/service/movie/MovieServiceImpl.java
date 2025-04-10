package utc.cinemas.service.movie;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import utc.cinemas.model.dto.MoviesDto;
import utc.cinemas.model.dto.Response;
import utc.cinemas.model.dto.ResponseCode;
import utc.cinemas.model.entity.Movie;
import utc.cinemas.repository.MovieRepository;
import utc.cinemas.util.DatabaseUtils;
import utc.cinemas.util.Utils;

import java.util.Map;

@Service
@Slf4j
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public Response getListOfMovies(Map<String, String> filters) {
        try {
            String search = filters.getOrDefault("search", "").trim();
            Map<String, Object> result = DatabaseUtils.getList(filters, pageable -> movieRepository.findAll("%" + search + "%", pageable));
            return Utils.createResponse(ResponseCode.SUCCESS, result);
        } catch (Exception e) {
            log.error("Error fetching movies: {}", e.getMessage());
            return Utils.createResponse(ResponseCode.ERROR);
        }
    }

    @Override
    public Response create(MoviesDto moviesDto) {
        try {
            Movie movie = moviesDto.getEntity();
            DatabaseUtils.createEntity(movie, movieRepository);
            return Utils.createResponse(ResponseCode.SUCCESS, "Thêm phim mới thành công");
        } catch (DataIntegrityViolationException e) {
            return Utils.createResponse(ResponseCode.ERROR, "Tên phim đã tồn tại");
        } catch (Exception e) {
            log.error("Error adding movie: {}", e.getMessage(), e);
            return Utils.createResponse(ResponseCode.ERROR, "Thêm phim mới thất bại");
        }
    }
}
