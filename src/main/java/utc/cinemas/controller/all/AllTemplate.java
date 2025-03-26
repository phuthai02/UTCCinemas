package utc.cinemas.controller.all;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import utc.cinemas.util.AuthUtils;

@Controller
@RequestMapping("/utc-cinemas")
public class AllTemplate {
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
}
