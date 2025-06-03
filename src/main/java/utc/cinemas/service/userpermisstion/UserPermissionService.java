package utc.cinemas.service.userpermisstion;

import utc.cinemas.model.entity.Permission;

import java.util.List;

public interface UserPermissionService {
    List<String> getUserPermissions(Long userId);
    List<Permission> getAllActivePermissions();
    List<String> getAllPermissionCodes();
}
