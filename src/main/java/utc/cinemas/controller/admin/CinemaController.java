package utc.cinemas.controller.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utc.cinemas.model.dto.CinemasDto;
import utc.cinemas.model.dto.Response;
import utc.cinemas.service.cinema.CinemaService;
import utc.cinemas.util.JsonUtils;

import java.util.Map;

@RestController
@RequestMapping("/api/admin/cinemas")
@Slf4j
public class CinemaController {

    @Autowired
    private CinemaService cinemaService;

    @GetMapping("get-list")
    public ResponseEntity<Response> getCinemas(
            @RequestParam Map<String, String> filters) {
        log.info("Get list cinemas with params: filters={}", filters);
        Response response = cinemaService.getListOfCinemas(filters);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("create")
    public ResponseEntity<Response> createCinema(
            @RequestBody CinemasDto cinemasDto) {
        log.info("Create cinemas with params: dto={}", JsonUtils.toString(cinemasDto));
        Response response = cinemaService.create(cinemasDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
