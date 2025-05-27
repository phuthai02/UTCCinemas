package utc.cinemas.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "SHOWTIMES")
public class Showtime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID") // Mã suất chiếu
    private Long id;

    @Column(name = "MOVIE_ID") // Mã phim
    private Long movieId;

    @Column(name = "ROOM_ID") // Mã phòng chiếu
    private Long roomId;

    @Column(name = "START_TIME") // Giờ bắt đầu chiếu
    private Timestamp startTime;

    @Column(name = "PRICE") // Giá vé
    private Long price;

    @Column(name = "CREATED_USER") // Người tạo
    private Long createdUser;

    @Column(name = "MODIFIED_USER") // Người chỉnh sửa cuối
    private Long modifiedUser;

    @Column(name = "CREATED_DATE") // Ngày tạo
    private Timestamp createdDate;

    @Column(name = "MODIFIED_DATE") // Ngày chỉnh sửa cuối
    private Timestamp modifiedDate;

    @Column(name = "STATUS")
    private Integer status; // Trạng thái

    @Column(name = "DISPLAY")
    private Integer display; // Hiển thị (1: hiển thị, 0: ẩn)
}
