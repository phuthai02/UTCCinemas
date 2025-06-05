package utc.cinemas.util;

import java.util.Arrays;
import java.util.List;

public class Constants {
    // Existing constants
    public static final Integer ROLE_DEVELOPER = 2002;
    public static final Integer ROLE_USER = 0;
    public static final Integer ROLE_CEO = 1;
    public static final Integer ROLE_MANAGER = 2;
    public static final Integer ROLE_TECHNICAL_STAFF = 3;
    public static final Integer ROLE_SALES_STAFF = 4;

    public static final Integer SEAT_TYPE_VIP = 0;
    public static final Integer SEAT_TYPE_NORMAL = 1;
    public static final Integer SEAT_TYPE_COUPLE = 2;
    public static final Integer SEAT_TYPE_ACCESSIBLE = 3;

    public static final Integer STATUS_ACTIVE = 1;
    public static final Integer STATUS_INACTIVE = 0;

    public static final Integer DISPLAY_HIDDEN = 0;
    public static final Integer DISPLAY_VISIBLE = 1;

    // Permission Constants
    // Cinema Management Permissions
    public static final String CINEMA_VIEW = "CINEMA_VIEW";
    public static final String CINEMA_CREATE = "CINEMA_CREATE";
    public static final String CINEMA_EDIT = "CINEMA_EDIT";
    public static final String CINEMA_DELETE = "CINEMA_DELETE";

    // Product Management Permissions
    public static final String PRODUCT_VIEW = "PRODUCT_VIEW";
    public static final String PRODUCT_CREATE = "PRODUCT_CREATE";
    public static final String PRODUCT_EDIT = "PRODUCT_EDIT";
    public static final String PRODUCT_DELETE = "PRODUCT_DELETE";

    // Room Management Permissions
    public static final String ROOM_VIEW = "ROOM_VIEW";
    public static final String ROOM_CREATE = "ROOM_CREATE";
    public static final String ROOM_EDIT = "ROOM_EDIT";
    public static final String ROOM_DELETE = "ROOM_DELETE";

    // Seat Management Permissions
    public static final String SEAT_VIEW = "SEAT_VIEW";
    public static final String SEAT_CREATE = "SEAT_CREATE";
    public static final String SEAT_EDIT = "SEAT_EDIT";
    public static final String SEAT_DELETE = "SEAT_DELETE";

    // Movie Management Permissions
    public static final String MOVIE_VIEW = "MOVIE_VIEW";
    public static final String MOVIE_CREATE = "MOVIE_CREATE";
    public static final String MOVIE_EDIT = "MOVIE_EDIT";
    public static final String MOVIE_DELETE = "MOVIE_DELETE";

    // Showtime Management Permissions
    public static final String SHOWTIME_VIEW = "SHOWTIME_VIEW";
    public static final String SHOWTIME_CREATE = "SHOWTIME_CREATE";
    public static final String SHOWTIME_EDIT = "SHOWTIME_EDIT";
    public static final String SHOWTIME_DELETE = "SHOWTIME_DELETE";

    // Ticket Management Permissions
    public static final String TICKET_VIEW = "TICKET_VIEW";
    public static final String TICKET_CREATE = "TICKET_CREATE";
    public static final String TICKET_EDIT = "TICKET_EDIT";
    public static final String TICKET_DELETE = "TICKET_DELETE";

    // Equipment Management Permissions
    public static final String EQUIPMENT_VIEW = "EQUIPMENT_VIEW";
    public static final String EQUIPMENT_CREATE = "EQUIPMENT_CREATE";
    public static final String EQUIPMENT_EDIT = "EQUIPMENT_EDIT";
    public static final String EQUIPMENT_DELETE = "EQUIPMENT_DELETE";

    // Staff Management Permissions
    public static final String STAFF_VIEW = "STAFF_VIEW";
    public static final String STAFF_CREATE = "STAFF_CREATE";
    public static final String STAFF_EDIT = "STAFF_EDIT";
    public static final String STAFF_DELETE = "STAFF_DELETE";

    // Customer Management Permissions
    public static final String CUSTOMER_VIEW = "CUSTOMER_VIEW";
    public static final String CUSTOMER_CREATE = "CUSTOMER_CREATE";
    public static final String CUSTOMER_EDIT = "CUSTOMER_EDIT";
    public static final String CUSTOMER_DELETE = "CUSTOMER_DELETE";

    // Permission Management Permissions
    public static final String PERMISSION_VIEW = "PERMISSION_VIEW";
    public static final String PERMISSION_CREATE = "PERMISSION_CREATE";
    public static final String PERMISSION_EDIT = "PERMISSION_EDIT";
    public static final String PERMISSION_DELETE = "PERMISSION_DELETE";

    // User Permission Management
    public static final String USER_PERMISSION_VIEW = "USER_PERMISSION_VIEW";
    public static final String USER_PERMISSION_ASSIGN = "USER_PERMISSION_ASSIGN";

    // Report Permissions
    public static final String REPORT_VIEW = "REPORT_VIEW";
    public static final String REPORT_EXPORT = "REPORT_EXPORT";

    // ==================== DEFAULT ROLE PERMISSIONS ====================
    // Developer
    public static final List<String> DEVELOPER_DEFAULT_PERMISSIONS = Arrays.asList(
            CINEMA_VIEW, CINEMA_CREATE, CINEMA_EDIT, CINEMA_DELETE,
            PRODUCT_VIEW, PRODUCT_CREATE, PRODUCT_EDIT, PRODUCT_DELETE,
            ROOM_VIEW, ROOM_CREATE, ROOM_EDIT, ROOM_DELETE,
            SEAT_VIEW, SEAT_CREATE, SEAT_EDIT, SEAT_DELETE,
            MOVIE_VIEW, MOVIE_CREATE, MOVIE_EDIT, MOVIE_DELETE,
            SHOWTIME_VIEW, SHOWTIME_CREATE, SHOWTIME_EDIT, SHOWTIME_DELETE,
            TICKET_VIEW, TICKET_CREATE, TICKET_EDIT, TICKET_DELETE,
            EQUIPMENT_VIEW, EQUIPMENT_CREATE, EQUIPMENT_EDIT, EQUIPMENT_DELETE,
            STAFF_VIEW, STAFF_CREATE, STAFF_EDIT, STAFF_DELETE,
            CUSTOMER_VIEW, CUSTOMER_CREATE, CUSTOMER_EDIT, CUSTOMER_DELETE,
            PERMISSION_VIEW, PERMISSION_CREATE, PERMISSION_EDIT, PERMISSION_DELETE,
            USER_PERMISSION_VIEW, USER_PERMISSION_ASSIGN,
            REPORT_VIEW, REPORT_EXPORT
    );

    // Tong giam doc
    public static final List<String> CEO_DEFAULT_PERMISSIONS = Arrays.asList(
            PERMISSION_VIEW, PERMISSION_CREATE, PERMISSION_EDIT, PERMISSION_DELETE,
            CINEMA_VIEW, CINEMA_CREATE, CINEMA_EDIT, CINEMA_DELETE,
            PRODUCT_VIEW, PRODUCT_CREATE, PRODUCT_EDIT, PRODUCT_DELETE,
            ROOM_VIEW, ROOM_CREATE, ROOM_EDIT, ROOM_DELETE,
            SEAT_VIEW, SEAT_CREATE, SEAT_EDIT, SEAT_DELETE,
            MOVIE_VIEW, MOVIE_CREATE, MOVIE_EDIT, MOVIE_DELETE,
            SHOWTIME_VIEW, SHOWTIME_CREATE, SHOWTIME_EDIT, SHOWTIME_DELETE,
            TICKET_VIEW, TICKET_CREATE, TICKET_EDIT, TICKET_DELETE,
            EQUIPMENT_VIEW, EQUIPMENT_CREATE, EQUIPMENT_EDIT, EQUIPMENT_DELETE,
            STAFF_VIEW, STAFF_CREATE, STAFF_EDIT, STAFF_DELETE,
            CUSTOMER_VIEW, CUSTOMER_CREATE, CUSTOMER_EDIT, CUSTOMER_DELETE,
            USER_PERMISSION_VIEW, USER_PERMISSION_ASSIGN,
            REPORT_VIEW, REPORT_EXPORT
    );

    // Giam doc chi nhanh
    public static final List<String> MANAGER_DEFAULT_PERMISSIONS = Arrays.asList(
            CINEMA_VIEW,
            PRODUCT_VIEW, PRODUCT_CREATE, PRODUCT_EDIT, PRODUCT_DELETE,
            ROOM_VIEW, ROOM_CREATE, ROOM_EDIT, ROOM_DELETE,
            SEAT_VIEW, SEAT_CREATE, SEAT_EDIT, SEAT_DELETE,
            MOVIE_VIEW, MOVIE_CREATE, MOVIE_EDIT, MOVIE_DELETE,
            SHOWTIME_VIEW, SHOWTIME_CREATE, SHOWTIME_EDIT, SHOWTIME_DELETE,
            TICKET_VIEW, TICKET_CREATE, TICKET_EDIT, TICKET_DELETE,
            EQUIPMENT_VIEW, EQUIPMENT_CREATE, EQUIPMENT_EDIT, EQUIPMENT_DELETE,
            STAFF_VIEW, STAFF_CREATE, STAFF_EDIT, STAFF_DELETE,
            CUSTOMER_VIEW, CUSTOMER_CREATE, CUSTOMER_EDIT, CUSTOMER_DELETE,
            REPORT_VIEW, REPORT_EXPORT
    );

    // Nhan vien ky thuat
    public static final List<String> TECHNICAL_STAFF_DEFAULT_PERMISSIONS = Arrays.asList(
            EQUIPMENT_VIEW, EQUIPMENT_CREATE, EQUIPMENT_EDIT,
            ROOM_VIEW, ROOM_EDIT,
            SEAT_VIEW, SEAT_CREATE, SEAT_EDIT, SEAT_DELETE
    );

    // Nhan vien ban ve
    public static final List<String> SALES_STAFF_DEFAULT_PERMISSIONS = Arrays.asList(
            TICKET_VIEW, TICKET_EDIT,
            CUSTOMER_VIEW, CUSTOMER_CREATE, CUSTOMER_EDIT,
            PRODUCT_VIEW,
            MOVIE_VIEW,
            SHOWTIME_VIEW
    );


    public static List<String> getDefaultPermissionsForRole(Integer roleId) {
        switch (roleId) {
            case 2002:
                return DEVELOPER_DEFAULT_PERMISSIONS;
            case 1:
                return CEO_DEFAULT_PERMISSIONS;
            case 2:
                return MANAGER_DEFAULT_PERMISSIONS;
            case 3:
                return TECHNICAL_STAFF_DEFAULT_PERMISSIONS;
            case 4:
                return SALES_STAFF_DEFAULT_PERMISSIONS;
            default:
                return Arrays.asList();
        }
    }
}