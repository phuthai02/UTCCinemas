package utc.cinemas.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "PAYMENTS")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID") // Mã thanh toán
    private Long id;

    @Column(name = "USER_ID") // Mã người dùng
    private Long userId;

    @Column(name = "TICKET_ID") // Mã vé
    private Long ticketId;

    @Column(name = "AMOUNT") // Số tiền thanh toán
    private Long amount;

    @Column(name = "PAYMENT_STATUS") // Trạng thái thanh toán (0: Chưa thanh toán, 1: Đã thanh toán)
    private Integer paymentStatus;

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
