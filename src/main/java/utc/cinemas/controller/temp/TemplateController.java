package utc.cinemas.controller.temp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import utc.cinemas.util.AuthUtils;

@Controller
@RequestMapping("/utc-cinemas")
public class TemplateController {

    private static final String BASE_LAYOUT = "layouts/base-layout";

    private String renderPage(Model model, String activeMenu, String contentTemplate, String pageTitle) {
        model.addAttribute("activeMenu", activeMenu);
        model.addAttribute("contentTemplate", contentTemplate);
        model.addAttribute("pageTitle", pageTitle);
        return BASE_LAYOUT;
    }

    @GetMapping("/login")
    public String login() {
        if (AuthUtils.isAuthenticated()) {
            return "redirect:/utc-cinemas/home";
        }
        return "layouts/login";
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "layouts/access-denied";
    }

    @GetMapping("/home")
    public String home(Model model) {
        return renderPage(model, "home", "contents/home", "Trang chủ - UTC Cinemas");
    }

    @GetMapping("/cinemas")
    public String cinemas(Model model) {
        return renderPage(model, "cinemas", "contents/cinemas/view", "Quản lý rạp chiếu - UTC Cinemas");
    }

    @GetMapping("/cinemas/create")
    public String createCinema(Model model) {
        return renderPage(model, "cinemas", "contents/cinemas/form", "Thêm rạp chiếu - UTC Cinemas");
    }

    @GetMapping("/cinemas/edit")
    public String editCinema(@RequestParam String id, Model model) {
        model.addAttribute("id", id);
        return renderPage(model, "cinemas", "contents/cinemas/form", "Sửa rạp chiếu - UTC Cinemas");
    }

    @GetMapping("/rooms")
    public String rooms(Model model) {
        return renderPage(model, "rooms", "contents/rooms/view", "Quản lý phòng chiếu - UTC Cinemas");
    }

    @GetMapping("/rooms/create")
    public String createRoom(Model model) {
        return renderPage(model, "rooms", "contents/rooms/form", "Thêm phòng chiếu - UTC Cinemas");
    }

    @GetMapping("/rooms/edit")
    public String editRoom(@RequestParam String id, Model model) {
        model.addAttribute("id", id);
        return renderPage(model, "rooms", "contents/rooms/form", "Sửa phòng chiếu - UTC Cinemas");
    }

    @GetMapping("/seats")
    public String seats(Model model) {
        return renderPage(model, "seats", "contents/seats/view", "Quản lý ghế ngồi - UTC Cinemas");
    }

    @GetMapping("/seats/create")
    public String createSeat(Model model) {
        return renderPage(model, "seats", "contents/seats/form", "Thêm ghế ngồi - UTC Cinemas");
    }

    @GetMapping("/seats/edit")
    public String editSeat(@RequestParam String id, Model model) {
        model.addAttribute("id", id);
        return renderPage(model, "seats", "contents/seats/form", "Sửa ghế ngồi - UTC Cinemas");
    }

    @GetMapping("/movies")
    public String movies(Model model) {
        return renderPage(model, "movies", "contents/movies/view", "Quản lý phim - UTC Cinemas");
    }

    @GetMapping("/movies/create")
    public String createMovie(Model model) {
        return renderPage(model, "movies", "contents/movies/form", "Thêm phim - UTC Cinemas");
    }

    @GetMapping("/movies/edit")
    public String editMovie(@RequestParam String id, Model model) {
        model.addAttribute("id", id);
        return renderPage(model, "movies", "contents/movies/form", "Sửa phim - UTC Cinemas");
    }

    @GetMapping("/showtimes")
    public String showtimes(Model model) {
        return renderPage(model, "showtimes", "contents/showtimes/view", "Quản lý suất chiếu - UTC Cinemas");
    }

    @GetMapping("/showtimes/create")
    public String createShowtime(Model model) {
        return renderPage(model, "showtimes", "contents/showtimes/form", "Thêm suất chiếu - UTC Cinemas");
    }

    @GetMapping("/showtimes/edit")
    public String editShowtime(@RequestParam String id, Model model) {
        model.addAttribute("id", id);
        return renderPage(model, "showtimes", "contents/showtimes/form", "Sửa suất chiếu - UTC Cinemas");
    }

    @GetMapping("/tickets")
    public String tickets(Model model) {
        return renderPage(model, "tickets", "contents/tickets/view", "Quản lý đặt vé - UTC Cinemas");
    }

    @GetMapping("/reports")
    public String reports(Model model) {
        return renderPage(model, "reports", "contents/reports/view", "Thống kê báo cáo - UTC Cinemas");
    }

    @GetMapping("/equipments")
    public String equipments(Model model) {
        return renderPage(model, "equipments", "contents/equipments/view", "Quản lý trang thiết bị - UTC Cinemas");
    }

    @GetMapping("/equipments/create")
    public String createEquipment(Model model) {
        return renderPage(model, "equipments", "contents/equipments/form", "Thêm trang thiết bị - UTC Cinemas");
    }

    @GetMapping("/equipments/edit")
    public String editEquipment(@RequestParam String id, Model model) {
        model.addAttribute("id", id);
        return renderPage(model, "equipments", "contents/equipments/form", "Sửa trang thiết bị - UTC Cinemas");
    }

    @GetMapping("/permissions")
    public String permissions(Model model) {
        return renderPage(model, "permissions", "contents/permissions/view", "Quản lý quyền - UTC Cinemas");
    }

    @GetMapping("/permissions/create")
    public String createPermission(Model model) {
        return renderPage(model, "permissions", "contents/permissions/form", "Thêm quyền - UTC Cinemas");
    }

    @GetMapping("/permissions/edit")
    public String editPermission(@RequestParam String id, Model model) {
        model.addAttribute("id", id);
        return renderPage(model, "permissions", "contents/permissions/form", "Sửa quyền - UTC Cinemas");
    }

    @GetMapping("/staffs")
    public String staff(Model model) {
        return renderPage(model, "staffs", "contents/staffs/view", "Quản lý nhân viên - UTC Cinemas");
    }

    @GetMapping("/staffs/create")
    public String createStaff(Model model) {
        return renderPage(model, "staffs", "contents/staffs/form", "Thêm nhân viên - UTC Cinemas");
    }

    @GetMapping("/staffs/edit")
    public String editStaff(@RequestParam String id, Model model) {
        model.addAttribute("id", id);
        return renderPage(model, "staffs", "contents/staffs/form", "Sửa nhân viên - UTC Cinemas");
    }

    @GetMapping("/customers")
    public String customers(Model model) {
        return renderPage(model, "customers", "contents/customers/view", "Quản lý khách hàng - UTC Cinemas");
    }

    @GetMapping("/customers/create")
    public String createCustomer(Model model) {
        return renderPage(model, "customers", "contents/customers/form", "Thêm khách hàng - UTC Cinemas");
    }

    @GetMapping("/customers/edit")
    public String editCustomer(@RequestParam String id, Model model) {
        model.addAttribute("id", id);
        return renderPage(model, "customers", "contents/customers/form", "Sửa khách hàng - UTC Cinemas");
    }

    @GetMapping("/users-permissions")
    public String usersPermissions(Model model) {
        return renderPage(model, "users-permissions", "contents/users-permissions/view", "Phân quyền người dùng - UTC Cinemas");
    }
}