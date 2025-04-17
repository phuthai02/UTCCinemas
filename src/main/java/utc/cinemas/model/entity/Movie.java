package utc.cinemas.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "MOVIES")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // ID phim

    @Column(name = "TITLE")
    private String title; // Tên phim

    @Column(name = "GENRE")
    private String genre; // Thể loại phim

    @Column(name = "DURATION")
    private Integer duration; // Thời lượng phim (phút)

    @Column(name = "DESCRIPTION")
    private String description; // Mô tả phim

    @Column(name = "RELEASE_DATE")
    private Timestamp releaseDate; // Ngày phát hành

    @Column(name = "RATING")
    private Integer rating; // Xếp hạng phim (theo điểm)

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

    @Column(name = "DIRECTOR")
    private String director; // Đạo diễn

    @Column(name = "IMAGE")
    private String image; // Đường dẫn hoặc URL hình ảnh
}
