package utc.cinemas.model.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import utc.cinemas.model.entity.Permission;

import java.sql.Timestamp;

@Data
public class PermissionDto {
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

    public Permission getEntity() {
        Permission permission = new Permission();
        BeanUtils.copyProperties(this, permission);
        return permission;
    }
}