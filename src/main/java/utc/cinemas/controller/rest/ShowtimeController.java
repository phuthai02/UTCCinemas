package utc.cinemas.controller.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utc.cinemas.model.dto.Response;
import utc.cinemas.model.dto.ShowtimeDto;
import utc.cinemas.service.showtime.ShowtimeService;
import utc.cinemas.util.JsonUtils;

import java.util.Map;

@RestController
@RequestMapping("/api/admin/showtimes")
@Slf4j
public class ShowtimeController {

    @Autowired
    private ShowtimeService showtimeService;

    @GetMapping("get-list")
    public ResponseEntity<Response> getShowtimes(@RequestParam Map<String, String> filters) {
        log.info("Get list showtimes with params: filters={}", filters);
        Response response = showtimeService.getListOfShowtimes(filters);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("get-all")
    public ResponseEntity<Response> getAll() {
        log.info("Get all showtimes");
        Response response = showtimeService.getAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Response> getShowtimeById(@PathVariable Long id) {
        log.info("Get showtime by id: id={}", id);
        Response response = showtimeService.getShowtimeById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("create")
    public ResponseEntity<Response> createShowtime(@RequestBody ShowtimeDto showtimeDto) {
        log.info("Create showtime with params: dto={}", JsonUtils.toString(showtimeDto));
        Response response = showtimeService.create(showtimeDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("update")
    public ResponseEntity<Response> updateShowtime(@RequestBody ShowtimeDto showtimeDto) {
        log.info("Update showtime with params: dto={}", JsonUtils.toString(showtimeDto));
        Response response = showtimeService.update(showtimeDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("toggle-status/{id}")
    public ResponseEntity<Response> toggleStatus(@PathVariable Long id) {
        log.info("Toggle status id={}", id);
        Response response = showtimeService.toggleStatus(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Response> delete(@PathVariable Long id) {
        log.info("Delete id={}", id);
        Response response = showtimeService.delete(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}