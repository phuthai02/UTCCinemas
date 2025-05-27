package utc.cinemas.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "USERS_PERMISSIONS")
public class UserPermission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long permissionId;
    private Long grantedUser;
    private Timestamp grantedDate;
    private Timestamp expiresDate;
    private Integer status;
    private Long createdUser;
    private Long modifiedUser;
    private Timestamp createdDate;
    private Timestamp modifiedDate;
    private Integer display;
}
