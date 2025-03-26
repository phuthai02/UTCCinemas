package utc.cinemas.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // ID người dùng

    @Column(name = "NAME")
    private String name; // Tên hiển thị

    @Column(name = "USERNAME")
    private String username; // Tên đăng nhập

    @Column(name = "PASSWORD")
    private String password; // Mật khẩu

    @Column(name = "EMAIL")
    private String email; // Email liên hệ

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber; // Số điện thoại

    @Column(name = "ROLE")
    private Integer role; // Vai trò người dùng (0: Admin, 1: User)

    @Column(name = "CREATED_USER")
    private Long createdUser; // Người tạo

    @Column(name = "MODIFIED_USER")
    private Long modifiedUser; // Người chỉnh sửa

    @Column(name = "CREATED_DATE")
    private Timestamp createdDate; // Ngày tạo tài khoản

    @Column(name = "MODIFIED_DATE")
    private Timestamp modifiedDate; // Ngày cập nhật tài khoản

    @Column(name = "STATUS")
    private Integer status; // Trạng thái
}
