package utc.cinemas.controller.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utc.cinemas.model.dto.MoviesDto;
import utc.cinemas.model.dto.Response;
import utc.cinemas.service.movie.MovieService;
import utc.cinemas.util.JsonUtils;

import java.util.Map;

@RestController
@RequestMapping("/api/admin/movies")
@Slf4j
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("get-list")
    public ResponseEntity<Response> getMovies(
            @RequestParam Map<String, String> filters) {
        log.info("Get list movies with params: filters={}", filters);
        Response response = movieService.getListOfMovies(filters);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("create")
    public ResponseEntity<Response> createMovie(
            @RequestBody MoviesDto moviesDto) {
        log.info("Create movie with params: dto={}", JsonUtils.toString(moviesDto));
        Response response = movieService.create(moviesDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
