package utc.cinemas.service.movie;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import utc.cinemas.model.dto.MovieDto;
import utc.cinemas.model.dto.Response;
import utc.cinemas.model.dto.ResponseCode;
import utc.cinemas.model.entity.Movie;
import utc.cinemas.repository.MovieRepository;
import utc.cinemas.util.DatabaseUtils;
import utc.cinemas.util.ImageUtils;
import utc.cinemas.util.JsonUtils;
import utc.cinemas.util.Utils;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public Response getListOfMovies(Map<String, String> filters) {
        try {
            String search = JsonUtils.convert(filters.get("search"), String.class).trim();
            Integer status = JsonUtils.convert(filters.get("status"), Integer.class);
            Map<String, Object> result = DatabaseUtils.getList(filters, pageable -> movieRepository.findAll("%" + search + "%", status, pageable));
            return Utils.createResponse(ResponseCode.SUCCESS, result);
        } catch (Exception e) {
            log.error("Error fetching movies: {}", e.getMessage());
            return Utils.createResponse(ResponseCode.ERROR);
        }
    }

    @Override
    public Response create(MovieDto movieDto) {
        String imagePath = null;
        try {
            Movie movie = movieDto.getEntity();
            imagePath = ImageUtils.storeImage(movieDto.getImageFile());
            movie.setImage(imagePath);
            DatabaseUtils.createEntity(movie, movieRepository);
            return Utils.createResponse(ResponseCode.SUCCESS, "Thêm phim mới thành công");
        } catch (DataIntegrityViolationException e) {
            if (imagePath != null) rollbackImage(imagePath);
            return Utils.createResponse(ResponseCode.ERROR, "Tên phim đã tồn tại");
        } catch (Exception e) {
            if (imagePath != null) rollbackImage(imagePath);
            log.error("Error adding movie: {}", e.getMessage(), e);
            return Utils.createResponse(ResponseCode.ERROR, "Thêm phim mới thất bại");
        }
    }

    @Override
    public Response getMovieById(Long id) {
        try {
            Movie movie = movieRepository.findById(id).orElse(null);
            return Utils.createResponse(ResponseCode.SUCCESS, movie);
        } catch (Exception e) {
            log.error("Error getting movie: {}", e.getMessage());
            return Utils.createResponse(ResponseCode.ERROR, "Không thể tải thông tin phim");
        }
    }

    @Override
    public Response update(MovieDto movieDto) {
        String imagePath = null;
        try {
            Movie movie = movieDto.getEntity();
            if (movieDto.getImageFile() != null) {
                ImageUtils.deleteImage(movie.getImage());
                imagePath = ImageUtils.storeImage(movieDto.getImageFile());
            } else movie.setImage(null);
            movie.setImage(imagePath);
            DatabaseUtils.updateEntity(movie, movieRepository);
            return Utils.createResponse(ResponseCode.SUCCESS, "Cập nhật phim thành công");
        } catch (DataIntegrityViolationException e) {
            if (imagePath != null) rollbackImage(imagePath);
            return Utils.createResponse(ResponseCode.ERROR, "Tên phim đã tồn tại");
        } catch (Exception e) {
            if (imagePath != null) rollbackImage(imagePath);
            log.error("Error editing movie: {}", e.getMessage(), e);
            return Utils.createResponse(ResponseCode.ERROR, "Cập nhật phim thất bại");
        }
    }

    @Override
    public Response getAll() {
        try {
            List<Movie> movies = movieRepository.findAll();
            return Utils.createResponse(ResponseCode.SUCCESS, movies);
        } catch (Exception e) {
            log.error("Error fetching movies all: {}", e.getMessage());
            return Utils.createResponse(ResponseCode.ERROR);
        }
    }

    private void rollbackImage(String imagePath) {
        try {
            ImageUtils.deleteImage(imagePath);
        } catch (Exception ex) {
            log.warn("Không thể xóa ảnh khi rollback: {}", ex.getMessage());
        }
    }
}
