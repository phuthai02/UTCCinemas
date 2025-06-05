package utc.cinemas.controller.rest;

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

    @GetMapping("get-list-staff")
    public ResponseEntity<Response> getStaffs(@RequestParam Map<String, String> filters) {
        log.info("Get list staffs with params: filters={}", filters);
        Response response = userService.getListOfStaffs(filters);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("get-list-consumer")
    public ResponseEntity<Response> getConsumers(@RequestParam Map<String, String> filters) {
        log.info("Get list consumers with params: filters={}", filters);
        Response response = userService.getListOfConsumers(filters);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Response> getUserById(@PathVariable Long id) {
        log.info("Get user by id: id={}", id);
        Response response = userService.getUserById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("create-staff")
    public ResponseEntity<Response> createStaff(@RequestBody UserDto userDto) {
        log.info("Create staff with params: dto={}", JsonUtils.toString(userDto));
        Response response = userService.createStaffs(userDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("create-consumer")
    public ResponseEntity<Response> createConsumer(@RequestBody UserDto userDto) {
        log.info("Create consumer with params: dto={}", JsonUtils.toString(userDto));
        Response response = userService.createConsumers(userDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("update")
    public ResponseEntity<Response> updateUser(@RequestBody UserDto userDto) {
        log.info("Update user with params: dto={}", JsonUtils.toString(userDto));
        Response response = userService.update(userDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("get-all")
    public ResponseEntity<Response> getUsers() {
        log.info("Get all users");
        Response response = userService.getAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("toggle-status/{id}")
    public ResponseEntity<Response> toggleStatus(@PathVariable Long id) {
        log.info("Toggle status id={}", id);
        Response response = userService.toggleStatus(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Response> delete(@PathVariable Long id) {
        log.info("Delete id={}", id);
        Response response = userService.delete(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
