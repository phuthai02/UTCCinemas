package utc.cinemas.controller.temp;

import org.springframework.security.access.prepost.PreAuthorize;
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

    // CINEMA MANAGEMENT
    @GetMapping("/cinemas")
    @PreAuthorize("hasAuthority('CINEMA_VIEW')")
    public String cinemas(Model model) {
        return renderPage(model, "cinemas", "contents/cinemas/view", "Quản lý rạp chiếu - UTC Cinemas");
    }

    @GetMapping("/cinemas/create")
    @PreAuthorize("hasAuthority('CINEMA_CREATE')")
    public String createCinema(Model model) {
        return renderPage(model, "cinemas", "contents/cinemas/form", "Thêm rạp chiếu - UTC Cinemas");
    }

    @GetMapping("/cinemas/edit")
    @PreAuthorize("hasAuthority('CINEMA_EDIT')")
    public String editCinema(@RequestParam String id, Model model) {
        model.addAttribute("id", id);
        return renderPage(model, "cinemas", "contents/cinemas/form", "Sửa rạp chiếu - UTC Cinemas");
    }

    // ROOM MANAGEMENT
    @GetMapping("/rooms")
    @PreAuthorize("hasAuthority('ROOM_VIEW')")
    public String rooms(Model model) {
        return renderPage(model, "rooms", "contents/rooms/view", "Quản lý phòng chiếu - UTC Cinemas");
    }

    @GetMapping("/rooms/create")
    @PreAuthorize("hasAuthority('ROOM_CREATE')")
    public String createRoom(Model model) {
        return renderPage(model, "rooms", "contents/rooms/form", "Thêm phòng chiếu - UTC Cinemas");
    }

    @GetMapping("/rooms/edit")
    @PreAuthorize("hasAuthority('ROOM_EDIT')")
    public String editRoom(@RequestParam String id, Model model) {
        model.addAttribute("id", id);
        return renderPage(model, "rooms", "contents/rooms/form", "Sửa phòng chiếu - UTC Cinemas");
    }

    // SEAT MANAGEMENT
    @GetMapping("/seats")
    @PreAuthorize("hasAuthority('SEAT_VIEW')")
    public String seats(Model model) {
        return renderPage(model, "seats", "contents/seats/view", "Quản lý ghế ngồi - UTC Cinemas");
    }

    @GetMapping("/seats/create")
    @PreAuthorize("hasAuthority('SEAT_CREATE')")
    public String createSeat(Model model) {
        return renderPage(model, "seats", "contents/seats/form", "Thêm ghế ngồi - UTC Cinemas");
    }

    @GetMapping("/seats/edit")
    @PreAuthorize("hasAuthority('SEAT_EDIT')")
    public String editSeat(@RequestParam String id, Model model) {
        model.addAttribute("id", id);
        return renderPage(model, "seats", "contents/seats/form", "Sửa ghế ngồi - UTC Cinemas");
    }

    // MOVIE MANAGEMENT
    @GetMapping("/movies")
    @PreAuthorize("hasAuthority('MOVIE_VIEW')")
    public String movies(Model model) {
        return renderPage(model, "movies", "contents/movies/view", "Quản lý phim - UTC Cinemas");
    }

    @GetMapping("/movies/create")
    @PreAuthorize("hasAuthority('MOVIE_CREATE')")
    public String createMovie(Model model) {
        return renderPage(model, "movies", "contents/movies/form", "Thêm phim - UTC Cinemas");
    }

    @GetMapping("/movies/edit")
    @PreAuthorize("hasAuthority('MOVIE_EDIT')")
    public String editMovie(@RequestParam String id, Model model) {
        model.addAttribute("id", id);
        return renderPage(model, "movies", "contents/movies/form", "Sửa phim - UTC Cinemas");
    }

    // SHOWTIME MANAGEMENT
    @GetMapping("/showtimes")
    @PreAuthorize("hasAuthority('SHOWTIME_VIEW')")
    public String showtimes(Model model) {
        return renderPage(model, "showtimes", "contents/showtimes/view", "Quản lý suất chiếu - UTC Cinemas");
    }

    @GetMapping("/showtimes/create")
    @PreAuthorize("hasAuthority('SHOWTIME_CREATE')")
    public String createShowtime(Model model) {
        return renderPage(model, "showtimes", "contents/showtimes/form", "Thêm suất chiếu - UTC Cinemas");
    }

    @GetMapping("/showtimes/edit")
    @PreAuthorize("hasAuthority('SHOWTIME_EDIT')")
    public String editShowtime(@RequestParam String id, Model model) {
        model.addAttribute("id", id);
        return renderPage(model, "showtimes", "contents/showtimes/form", "Sửa suất chiếu - UTC Cinemas");
    }

    // TICKET MANAGEMENT
    @GetMapping("/tickets")
    @PreAuthorize("hasAuthority('TICKET_VIEW')")
    public String tickets(Model model) {
        return renderPage(model, "tickets", "contents/tickets/view", "Quản lý đặt vé - UTC Cinemas");
    }

    // REPORTS
    @GetMapping("/reports")
    @PreAuthorize("hasAuthority('REPORT_VIEW')")
    public String reports(Model model) {
        return renderPage(model, "reports", "contents/reports/view", "Thống kê báo cáo - UTC Cinemas");
    }

    // EQUIPMENT MANAGEMENT
    @GetMapping("/equipments")
    @PreAuthorize("hasAuthority('EQUIPMENT_VIEW')")
    public String equipments(Model model) {
        return renderPage(model, "equipments", "contents/equipments/view", "Quản lý trang thiết bị - UTC Cinemas");
    }

    @GetMapping("/equipments/create")
    @PreAuthorize("hasAuthority('EQUIPMENT_CREATE')")
    public String createEquipment(Model model) {
        return renderPage(model, "equipments", "contents/equipments/form", "Thêm trang thiết bị - UTC Cinemas");
    }

    @GetMapping("/equipments/edit")
    @PreAuthorize("hasAuthority('EQUIPMENT_EDIT')")
    public String editEquipment(@RequestParam String id, Model model) {
        model.addAttribute("id", id);
        return renderPage(model, "equipments", "contents/equipments/form", "Sửa trang thiết bị - UTC Cinemas");
    }

    // PERMISSION MANAGEMENT
    @GetMapping("/permissions")
    @PreAuthorize("hasAuthority('PERMISSION_VIEW')")
    public String permissions(Model model) {
        return renderPage(model, "permissions", "contents/permissions/view", "Quản lý quyền - UTC Cinemas");
    }

    @GetMapping("/permissions/create")
    @PreAuthorize("hasAuthority('PERMISSION_CREATE')")
    public String createPermission(Model model) {
        return renderPage(model, "permissions", "contents/permissions/form", "Thêm quyền - UTC Cinemas");
    }

    @GetMapping("/permissions/edit")
    @PreAuthorize("hasAuthority('PERMISSION_EDIT')")
    public String editPermission(@RequestParam String id, Model model) {
        model.addAttribute("id", id);
        return renderPage(model, "permissions", "contents/permissions/form", "Sửa quyền - UTC Cinemas");
    }

    // STAFF MANAGEMENT
    @GetMapping("/staffs")
    @PreAuthorize("hasAuthority('STAFF_VIEW')")
    public String staff(Model model) {
        return renderPage(model, "staffs", "contents/staffs/view", "Quản lý nhân viên - UTC Cinemas");
    }

    @GetMapping("/staffs/create")
    @PreAuthorize("hasAuthority('STAFF_CREATE')")
    public String createStaff(Model model) {
        return renderPage(model, "staffs", "contents/staffs/form", "Thêm nhân viên - UTC Cinemas");
    }

    @GetMapping("/staffs/edit")
    @PreAuthorize("hasAuthority('STAFF_EDIT')")
    public String editStaff(@RequestParam String id, Model model) {
        model.addAttribute("id", id);
        return renderPage(model, "staffs", "contents/staffs/form", "Sửa nhân viên - UTC Cinemas");
    }

    // CUSTOMER MANAGEMENT
    @GetMapping("/customers")
    @PreAuthorize("hasAuthority('CUSTOMER_VIEW')")
    public String customers(Model model) {
        return renderPage(model, "customers", "contents/customers/view", "Quản lý khách hàng - UTC Cinemas");
    }

    @GetMapping("/customers/create")
    @PreAuthorize("hasAuthority('CUSTOMER_CREATE')")
    public String createCustomer(Model model) {
        return renderPage(model, "customers", "contents/customers/form", "Thêm khách hàng - UTC Cinemas");
    }

    @GetMapping("/customers/edit")
    @PreAuthorize("hasAuthority('CUSTOMER_EDIT')")
    public String editCustomer(@RequestParam String id, Model model) {
        model.addAttribute("id", id);
        return renderPage(model, "customers", "contents/customers/form", "Sửa khách hàng - UTC Cinemas");
    }

    // USER PERMISSIONS MANAGEMENT
    @GetMapping("/users-permissions")
    @PreAuthorize("hasAuthority('USER_PERMISSION_VIEW')")
    public String usersPermissions(Model model) {
        return renderPage(model, "users-permissions", "contents/users-permissions/view", "Phân quyền người dùng - UTC Cinemas");
    }
}