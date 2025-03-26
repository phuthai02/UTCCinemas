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
    private Long id; // ID rạp chiếu

    @Column(name = "NAME")
    private String name; // Tên rạp

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
}
