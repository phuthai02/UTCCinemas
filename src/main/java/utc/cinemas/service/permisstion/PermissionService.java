package utc.cinemas.service.permisstion;

import utc.cinemas.model.dto.PermissionDto;
import utc.cinemas.model.dto.Response;

import java.util.Map;

public interface PermissionService {
    Response getListOfPermissions(Map<String, String> filters);
    Response getPermissionById(Long id);
    Response getAll();
    Response update(PermissionDto permissionDto);
    Response create(PermissionDto permissionDto);
    Response delete(Long id);
    Response toggleStatus(Long id);
}
