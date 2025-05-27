package utc.cinemas.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "PERMISSIONS")
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String permissionName;
    private String permissionCode;
    private String description;
    private String module;
    private String action;
    private Long createdUser;
    private Long modifiedUser;
    private Timestamp createdDate;
    private Timestamp modifiedDate;
    private Integer status;
    private Integer display;
}
