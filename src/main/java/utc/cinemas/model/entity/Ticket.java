package utc.cinemas.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "TICKETS")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID") // Mã vé
    private Long id;

    @Column(name = "USER_ID") // Mã người dùng
    private Long userId;

    @Column(name = "SHOWTIME_ID") // Mã suất chiếu
    private Long showtimeId;

    @Column(name = "SEAT_ID") // Mã ghế
    private Long seatId;

    @Column(name = "STATUS") // Trạng thái vé (0: Đã đặt, 1: Đã thanh toán)
    private Integer status;

    @Column(name = "CREATED_USER") // Người tạo
    private Long createdUser;

    @Column(name = "MODIFIED_USER") // Người chỉnh sửa cuối
    private Long modifiedUser;

    @Column(name = "CREATED_DATE") // Ngày tạo
    private Timestamp createdDate;

    @Column(name = "MODIFIED_DATE") // Ngày chỉnh sửa cuối
    private Timestamp modifiedDate;

    @Column(name = "DISPLAY")
    private Integer display; // Hiển thị (1: hiển thị, 0: ẩn)
}
