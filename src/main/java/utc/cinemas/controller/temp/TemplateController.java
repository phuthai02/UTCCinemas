package utc.cinemas.controller.temp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import utc.cinemas.util.AuthUtils;

@Controller
@RequestMapping("/utc-cinemas")
public class TemplateController {

    @GetMapping("/login")
    public String login() {
        if (AuthUtils.isAuthenticated()) {
            return "redirect:/utc-cinemas/home";
        }
        return "login";
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "access-denied";
    }

    @GetMapping("/home")
    public String home() {
        return "home/home";
    }

    @GetMapping("/cinemas")
    public String cinemas() {
        return "cinemas/view";
    }

    @GetMapping("/cinemas/create")
    public String createCinema() {
        return "cinemas/form";
    }

    @GetMapping("/cinemas/edit")
    public String editCinema(@RequestParam String id) {
        return "cinemas/form";
    }

    @GetMapping("/rooms")
    public String rooms() {
        return "rooms/view";
    }

    @GetMapping("/rooms/create")
    public String createRoom() {
        return "rooms/form";
    }

    @GetMapping("/rooms/edit")
    public String editRoom(@RequestParam String id) {
        return "rooms/form";
    }

    @GetMapping("/seats")
    public String seats() {
        return "seats/view";
    }

    @GetMapping("/seats/create")
    public String createSeat() {
        return "seats/form";
    }

    @GetMapping("/seats/edit")
    public String editSeat(@RequestParam String id) {
        return "seats/form";
    }

    @GetMapping("/users")
    public String users() {
        return "users/view";
    }

    @GetMapping("/users/create")
    public String createUser() {
        return "users/form";
    }

    @GetMapping("/users/edit")
    public String editUser(@RequestParam String id) {
        return "users/form";
    }

    @GetMapping("/movies")
    public String movies() {
        return "movies/view";
    }

    @GetMapping("/movies/create")
    public String createMovie() {
        return "movies/form";
    }

    @GetMapping("/movies/edit")
    public String editMovie(@RequestParam String id) {
        return "movies/form";
    }

    @GetMapping("/showtimes")
    public String showtimes() {
        return "showtimes/view";
    }

    @GetMapping("/showtimes/create")
    public String createShowtime() {
        return "showtimes/form";
    }

    @GetMapping("/showtimes/edit")
    public String editShowtime(@RequestParam String id) {
        return "showtimes/form";
    }

    @GetMapping("/tickets")
    public String tickets() {
        return "tickets/view";
    }

    @GetMapping("/reports")
    public String reports() {
        return "reports/view";
    }
}
