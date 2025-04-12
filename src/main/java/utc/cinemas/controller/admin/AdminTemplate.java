package utc.cinemas.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import utc.cinemas.util.AuthUtils;
import utc.cinemas.util.Constants;

@Controller
@RequestMapping("/utc-cinemas")
public class AdminTemplate {

    @GetMapping("/home")
    public String home() {
        if (!AuthUtils.isAuthenticated()) {
            return "redirect:/utc-cinemas/login";
        }
        if (!AuthUtils.hasRole(Constants.ROLE_ADMIN)) {
            return "redirect:/utc-cinemas/access-denied";
        }
        return "home/home";
    }

    @GetMapping("/cinemas")
    public String cinemas() {
        if (!AuthUtils.isAuthenticated()) {
            return "redirect:/utc-cinemas/login";
        }
        if (!AuthUtils.hasRole(Constants.ROLE_ADMIN)) {
            return "redirect:/utc-cinemas/access-denied";
        }
        return "cinemas/view";
    }

    @GetMapping("/cinemas/create")
    public String createCinemas() {
        if (!AuthUtils.isAuthenticated()) {
            return "redirect:/utc-cinemas/login";
        }
        if (!AuthUtils.hasRole(Constants.ROLE_ADMIN)) {
            return "redirect:/utc-cinemas/access-denied";
        }
        return "cinemas/form";
    }

    @GetMapping("/cinemas/edit")
    public String editCinemas(@RequestParam String id) {
        if (!AuthUtils.isAuthenticated()) {
            return "redirect:/utc-cinemas/login";
        }
        if (!AuthUtils.hasRole(Constants.ROLE_ADMIN)) {
            return "redirect:/utc-cinemas/access-denied";
        }
        return "cinemas/form";
    }

    @GetMapping("/rooms")
    public String rooms() {
        if (!AuthUtils.isAuthenticated()) {
            return "redirect:/utc-cinemas/login";
        }
        if (!AuthUtils.hasRole(Constants.ROLE_ADMIN)) {
            return "redirect:/utc-cinemas/access-denied";
        }
        return "rooms/view";
    }

    @GetMapping("/rooms/create")
    public String createRooms() {
        if (!AuthUtils.isAuthenticated()) {
            return "redirect:/utc-cinemas/login";
        }
        if (!AuthUtils.hasRole(Constants.ROLE_ADMIN)) {
            return "redirect:/utc-cinemas/access-denied";
        }
        return "rooms/form";
    }

    @GetMapping("/rooms/edit")
    public String editRooms(@RequestParam String id) {
        if (!AuthUtils.isAuthenticated()) {
            return "redirect:/utc-cinemas/login";
        }
        if (!AuthUtils.hasRole(Constants.ROLE_ADMIN)) {
            return "redirect:/utc-cinemas/access-denied";
        }
        return "rooms/form";
    }

    @GetMapping("/users")
    public String user() {
        if (!AuthUtils.isAuthenticated()) {
            return "redirect:/utc-cinemas/login";
        }
        if (!AuthUtils.hasRole(Constants.ROLE_ADMIN)) {
            return "redirect:/utc-cinemas/access-denied";
        }
        return "users/view";
    }

    @GetMapping("/movies")
    public String movies() {
        if (!AuthUtils.isAuthenticated()) {
            return "redirect:/utc-cinemas/login";
        }
        if (!AuthUtils.hasRole(Constants.ROLE_ADMIN)) {
            return "redirect:/utc-cinemas/access-denied";
        }
        return "movies/view";
    }

    @GetMapping("/movies/create")
    public String createMovie() {
        if (!AuthUtils.isAuthenticated()) {
            return "redirect:/utc-cinemas/login";
        }
        if (!AuthUtils.hasRole(Constants.ROLE_ADMIN)) {
            return "redirect:/utc-cinemas/access-denied";
        }
        return "movies/create";
    }
}
