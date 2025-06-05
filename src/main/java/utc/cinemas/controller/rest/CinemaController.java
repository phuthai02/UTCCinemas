package utc.cinemas.controller.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utc.cinemas.model.dto.CinemaDto;
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
    public ResponseEntity<Response> getCinemas(@RequestParam Map<String, String> filters) {
        log.info("Get list cinemas with params: filters={}", filters);
        Response response = cinemaService.getListOfCinemas(filters);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Response> getCinemaById(@PathVariable Long id) {
        log.info("Get cinema by id: id={}", id);
        Response response = cinemaService.getCinemaById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("create")
    public ResponseEntity<Response> createCinema(@RequestBody CinemaDto cinemaDto) {
        log.info("Create cinema with params: dto={}", JsonUtils.toString(cinemaDto));
        Response response = cinemaService.create(cinemaDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("update")
    public ResponseEntity<Response> updateCinema(@RequestBody CinemaDto cinemaDto) {
        log.info("Update cinema with params: dto={}", JsonUtils.toString(cinemaDto));
        Response response = cinemaService.update(cinemaDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("get-all")
    public ResponseEntity<Response> getCinemas() {
        log.info("Get all cinemas");
        Response response = cinemaService.getAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("toggle-status/{id}")
    public ResponseEntity<Response> toggleStatus(@PathVariable Long id) {
        log.info("Toggle status id={}", id);
        Response response = cinemaService.toggleStatus(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Response> delete(@PathVariable Long id) {
        log.info("Delete cinema id={}", id);
        Response response = cinemaService.delete(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
