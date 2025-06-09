package utc.cinemas.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "CINEMAS")
public class Cinema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // ID chi nhánh

    @Column(name = "NAME")
    private String name; // Tên chi nhánh

    @Column(name = "LOCATION")
    private String location; // Địa chỉ rạp

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

    @Column(name = "CINEMA_DIRECTOR")
    private Long cinemaDirector; // Giám đốc chi nhánh

    @Column(name = "EMAIL")
    private String email; // Email

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber; // Số điện thoại

    @Column(name = "OPENING_TIME")
    private String openingTime; // Giờ mở cửa

    @Column(name = "CLOSING_TIME")
    private String closingTime; // Giờ đóng cửa

    @Column(name = "DISPLAY")
    private Integer display; // Hiển thị (1: hiển thị, 0: ẩn)
}
