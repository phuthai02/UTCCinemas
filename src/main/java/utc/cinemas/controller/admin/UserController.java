package utc.cinemas.controller.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utc.cinemas.model.dto.Response;
import utc.cinemas.service.user.UserService;

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
}
