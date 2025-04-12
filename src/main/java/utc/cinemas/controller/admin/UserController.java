package utc.cinemas.controller.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utc.cinemas.model.dto.Response;
import utc.cinemas.model.dto.UserDto;
import utc.cinemas.service.user.UserService;
import utc.cinemas.util.JsonUtils;

import java.util.Map;

@RestController
@RequestMapping("/api/admin/users")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("directors")
    public ResponseEntity<Response> getDirectors() {
        log.info("Get directors");
        return ResponseEntity.ok(userService.getDirectors());
    }

    @GetMapping("get-list")
    public ResponseEntity<Response> getUsers(@RequestParam Map<String, String> filters) {
        log.info("Get list users with params: filters={}", filters);
        Response response = userService.getListOfUsers(filters);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Response> getUserById(@PathVariable Long id) {
        log.info("Get user by id: id={}", id);
        Response response = userService.getUserById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("create")
    public ResponseEntity<Response> createUser(@RequestBody UserDto userDto) {
        log.info("Create user with params: dto={}", JsonUtils.toString(userDto));
        Response response = userService.create(userDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("update")
    public ResponseEntity<Response> updateUser(@RequestBody UserDto userDto) {
        log.info("Update user with params: dto={}", JsonUtils.toString(userDto));
        Response response = userService.update(userDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
