package utc.cinemas.model.dto;

import lombok.Data;
import org.springframework.beans.BeanUtils;
import utc.cinemas.model.entity.UserPermission;

import java.sql.Timestamp;

@Data
public class UserPermissionDto {
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

    public UserPermission getEntity() {
        UserPermission userPermission = new UserPermission();
        BeanUtils.copyProperties(this, userPermission);
        return userPermission;
    }
}