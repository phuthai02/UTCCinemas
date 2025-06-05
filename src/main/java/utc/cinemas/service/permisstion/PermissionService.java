package utc.cinemas.service.permisstion;

import utc.cinemas.model.dto.PermissionDto;
import utc.cinemas.model.dto.Response;

import java.util.Map;

public interface PermissionService {
    Response getPermissionById(Long id);
    Response getListOfPermissions(Map<String, String> filters);
    Response getAllModules();
    Response getAll();
    Response update(PermissionDto permissionDto);
    Response create(PermissionDto permissionDto);
    Response toggleStatus(Long id);
    Response delete(Long id);
}
