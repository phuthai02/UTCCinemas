package utc.cinemas.controller.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utc.cinemas.model.dto.Response;
import utc.cinemas.model.dto.SeatDto;
import utc.cinemas.service.seat.SeatService;
import utc.cinemas.util.JsonUtils;

import java.util.Map;

@RestController
@RequestMapping("/api/admin/seats")
@Slf4j
public class SeatController {

    @Autowired
    private SeatService seatService;

    @GetMapping("get-list")
    public ResponseEntity<Response> getSeats(@RequestParam Map<String, String> filters) {
        log.info("Get list seats with params: filters={}", filters);
        Response response = seatService.getListOfSeats(filters);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Response> getSeatById(@PathVariable Long id) {
        log.info("Get seat by id: id={}", id);
        Response response = seatService.getSeatById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("create")
    public ResponseEntity<Response> createSeat(@RequestBody SeatDto seatDto) {
        log.info("Create seat with params: dto={}", JsonUtils.toString(seatDto));
        Response response = seatService.create(seatDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("update")
    public ResponseEntity<Response> updateSeat(@RequestBody SeatDto seatDto) {
        log.info("Update seat with params: dto={}", JsonUtils.toString(seatDto));
        Response response = seatService.update(seatDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
