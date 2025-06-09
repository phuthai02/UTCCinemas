package utc.cinemas.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "SEATS")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // ID ghế

    @Column(name = "ROOM_ID")
    private Long roomId; // ID phòng chiếu

    @Column(name = "SEAT_NUMBER")
    private String seatNumber; // Số ghế

    @Column(name = "SEAT_TYPE") // Hạng ghế
    private Integer seatType;

    @Column(name = "CREATED_USER")
    private Long createdUser; // Người tạo

    @Column(name = "MODIFIED_USER")
    private Long modifiedUser; // Người chỉnh sửa

    @Column(name = "CREATED_DATE")
    private Timestamp createdDate; // Ngày tạo

    @Column(name = "MODIFIED_DATE")
    private Timestamp modifiedDate; // Ngày cập nhật

    @Column(name = "STATUS")
    private Integer status; // Trạng thái

    @Column(name = "DISPLAY")
    private Integer display; // Hiển thị (1: hiển thị, 0: ẩn)
}
