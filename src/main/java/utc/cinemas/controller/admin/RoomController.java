package utc.cinemas.controller.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utc.cinemas.model.dto.Response;
import utc.cinemas.model.dto.RoomDto;
import utc.cinemas.service.room.RoomService;
import utc.cinemas.util.JsonUtils;

import java.util.Map;

@RestController
@RequestMapping("/api/admin/rooms")
@Slf4j
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping("get-list")
    public ResponseEntity<Response> getRooms(@RequestParam Map<String, String> filters) {
        log.info("Get list rooms with params: filters={}", filters);
        Response response = roomService.getListOfRooms(filters);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Response> getRoomById(@PathVariable Long id) {
        log.info("Get room by id: id={}", id);
        Response response = roomService.getRoomById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("create")
    public ResponseEntity<Response> createRoom(@RequestBody RoomDto roomDto) {
        log.info("Create room with params: dto={}", JsonUtils.toString(roomDto));
        Response response = roomService.create(roomDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("update")
    public ResponseEntity<Response> updateRoom(@RequestBody RoomDto roomDto) {
        log.info("Update room with params: dto={}", JsonUtils.toString(roomDto));
        Response response = roomService.update(roomDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("get-all")
    public ResponseEntity<Response> getRooms() {
        log.info("Get all rooms");
        Response response = roomService.getAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
