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
    public String createCinema() {
        if (!AuthUtils.isAuthenticated()) {
            return "redirect:/utc-cinemas/login";
        }
        if (!AuthUtils.hasRole(Constants.ROLE_ADMIN)) {
            return "redirect:/utc-cinemas/access-denied";
        }
        return "cinemas/form";
    }

    @GetMapping("/cinemas/edit")
    public String editCinema(@RequestParam String id) {
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
    public String createRoom() {
        if (!AuthUtils.isAuthenticated()) {
            return "redirect:/utc-cinemas/login";
        }
        if (!AuthUtils.hasRole(Constants.ROLE_ADMIN)) {
            return "redirect:/utc-cinemas/access-denied";
        }
        return "rooms/form";
    }

    @GetMapping("/rooms/edit")
    public String editRoom(@RequestParam String id) {
        if (!AuthUtils.isAuthenticated()) {
            return "redirect:/utc-cinemas/login";
        }
        if (!AuthUtils.hasRole(Constants.ROLE_ADMIN)) {
            return "redirect:/utc-cinemas/access-denied";
        }
        return "rooms/form";
    }

    @GetMapping("/seats")
    public String seats() {
        if (!AuthUtils.isAuthenticated()) {
            return "redirect:/utc-cinemas/login";
        }
        if (!AuthUtils.hasRole(Constants.ROLE_ADMIN)) {
            return "redirect:/utc-cinemas/access-denied";
        }
        return "seats/view";
    }

    @GetMapping("/seats/create")
    public String createSeat() {
        if (!AuthUtils.isAuthenticated()) {
            return "redirect:/utc-cinemas/login";
        }
        if (!AuthUtils.hasRole(Constants.ROLE_ADMIN)) {
            return "redirect:/utc-cinemas/access-denied";
        }
        return "seats/form";
    }

    @GetMapping("/seats/edit")
    public String editSeat(@RequestParam String id) {
        if (!AuthUtils.isAuthenticated()) {
            return "redirect:/utc-cinemas/login";
        }
        if (!AuthUtils.hasRole(Constants.ROLE_ADMIN)) {
            return "redirect:/utc-cinemas/access-denied";
        }
        return "seats/form";
    }

    @GetMapping("/users")
    public String users() {
        if (!AuthUtils.isAuthenticated()) {
            return "redirect:/utc-cinemas/login";
        }
        if (!AuthUtils.hasRole(Constants.ROLE_ADMIN)) {
            return "redirect:/utc-cinemas/access-denied";
        }
        return "users/view";
    }

    @GetMapping("/users/create")
    public String createUser() {
        if (!AuthUtils.isAuthenticated()) {
            return "redirect:/utc-cinemas/login";
        }
        if (!AuthUtils.hasRole(Constants.ROLE_ADMIN)) {
            return "redirect:/utc-cinemas/access-denied";
        }
        return "users/form";
    }

    @GetMapping("/users/edit")
    public String editUser(@RequestParam String id) {
        if (!AuthUtils.isAuthenticated()) {
            return "redirect:/utc-cinemas/login";
        }
        if (!AuthUtils.hasRole(Constants.ROLE_ADMIN)) {
            return "redirect:/utc-cinemas/access-denied";
        }
        return "users/form";
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
        return "movies/form";
    }

    @GetMapping("/movies/edit")
    public String editMovie(@RequestParam String id) {
        if (!AuthUtils.isAuthenticated()) {
            return "redirect:/utc-cinemas/login";
        }
        if (!AuthUtils.hasRole(Constants.ROLE_ADMIN)) {
            return "redirect:/utc-cinemas/access-denied";
        }
        return "movies/form";
    }

    @GetMapping("/showtimes")
    public String showtimes() {
        if (!AuthUtils.isAuthenticated()) {
            return "redirect:/utc-cinemas/login";
        }
        if (!AuthUtils.hasRole(Constants.ROLE_ADMIN)) {
            return "redirect:/utc-cinemas/access-denied";
        }
        return "showtimes/view";
    }

    @GetMapping("/showtimes/create")
    public String createShowtime() {
        if (!AuthUtils.isAuthenticated()) {
            return "redirect:/utc-cinemas/login";
        }
        if (!AuthUtils.hasRole(Constants.ROLE_ADMIN)) {
            return "redirect:/utc-cinemas/access-denied";
        }
        return "showtimes/form";
    }

    @GetMapping("/showtimes/edit")
    public String editShowtime(@RequestParam String id) {
        if (!AuthUtils.isAuthenticated()) {
            return "redirect:/utc-cinemas/login";
        }
        if (!AuthUtils.hasRole(Constants.ROLE_ADMIN)) {
            return "redirect:/utc-cinemas/access-denied";
        }
        return "showtimes/form";
    }

    @GetMapping("/tickets")
    public String tickets() {
        if (!AuthUtils.isAuthenticated()) {
            return "redirect:/utc-cinemas/login";
        }
        if (!AuthUtils.hasRole(Constants.ROLE_ADMIN)) {
            return "redirect:/utc-cinemas/access-denied";
        }
        return "tickets/view";
    }

    @GetMapping("/reports")
    public String reports() {
        if (!AuthUtils.isAuthenticated()) {
            return "redirect:/utc-cinemas/login";
        }
        if (!AuthUtils.hasRole(Constants.ROLE_ADMIN)) {
            return "redirect:/utc-cinemas/access-denied";
        }
        return "reports/view";
    }
}
