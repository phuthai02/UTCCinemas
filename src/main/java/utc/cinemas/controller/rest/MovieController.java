package utc.cinemas.controller.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utc.cinemas.model.dto.MovieDto;
import utc.cinemas.model.dto.Response;
import utc.cinemas.service.movie.MovieService;
import utc.cinemas.util.ImageUtils;
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

    @GetMapping("{id}")
    public ResponseEntity<Response> getCinemaById(@PathVariable Long id) {
        log.info("Get movie by id: id={}", id);
        Response response = movieService.getMovieById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("create")
    public ResponseEntity<Response> createMovie(@ModelAttribute MovieDto movieDto) {
        log.info("Create movie with params: dto={}", JsonUtils.toString(movieDto));
        Response response = movieService.create(movieDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("update")
    public ResponseEntity<Response> updateCinema(@ModelAttribute MovieDto movieDto) {
        log.info("Update movie with params: dto={}", JsonUtils.toString(movieDto));
        Response response = movieService.update(movieDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("get-image")
    public ResponseEntity<byte[]> getImageMovie(@RequestParam String imagePath) {
        return new ResponseEntity<>(ImageUtils.getImage(imagePath), HttpStatus.OK);
    }

    @GetMapping("get-all")
    public ResponseEntity<Response> getCinemas() {
        log.info("Get all movies");
        Response response = movieService.getAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
