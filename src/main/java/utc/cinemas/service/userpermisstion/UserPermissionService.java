package utc.cinemas.service.userpermisstion;

import utc.cinemas.model.dto.Response;
import utc.cinemas.model.dto.UserPermissionDto;
import utc.cinemas.model.entity.Permission;

import java.util.List;
import java.util.Map;

public interface UserPermissionService {
    Response getListOfUserPermissions(Map<String, String> filters);
    Response getUserPermissionById(Long id);
    Response getAll();
    Response create(UserPermissionDto userPermissionDto);
    Response update(UserPermissionDto userPermissionDto);
    Response delete(Long id);
    Response toggleStatus(Long id);
    List<String> getUserPermissions(Long userId);
    List<Permission> getAllActivePermissions();
    List<String> getAllPermissionCodes();
}
