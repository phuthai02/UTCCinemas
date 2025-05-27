package utc.cinemas.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "ROOMS")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // ID phòng chiếu

    @Column(name = "CINEMA_ID")
    private Long cinemaId; // ID rạp chiếu

    @Column(name = "NAME")
    private String name; // Tên phòng chiếu

    @Column(name = "SEAT_CAPACITY")
    private Integer seatCapacity; // Số ghế tối đa

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