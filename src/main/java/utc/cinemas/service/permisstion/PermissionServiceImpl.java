package utc.cinemas.service.permisstion;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utc.cinemas.model.dto.PermissionDto;
import utc.cinemas.model.dto.Response;
import utc.cinemas.model.dto.ResponseCode;
import utc.cinemas.model.entity.Permission;
import utc.cinemas.repository.PermissionRepository;
import utc.cinemas.util.Constants;
import utc.cinemas.util.DatabaseUtils;
import utc.cinemas.util.JsonUtils;
import utc.cinemas.util.Utils;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public Response getPermissionById(Long id) {
        try {
            Permission permission = permissionRepository.findById(id).orElse(null);
            return Utils.createResponse(ResponseCode.SUCCESS, permission);
        } catch (Exception e) {
            log.error("Error getting permission: {}", e.getMessage());
            return Utils.createResponse(ResponseCode.ERROR, "Không thể tải thông tin quyền");
        }
    }

    @Override
    public Response toggleStatus(Long id) {
        try {
            DatabaseUtils.toggleStatus(id, permissionRepository);
            return Utils.createResponse(ResponseCode.SUCCESS);
        } catch (EntityNotFoundException e) {
            log.error("Permission not found for ID {}: {}", id, e.getMessage());
            return Utils.createResponse(ResponseCode.ERROR, "Không thể tải thông tin quyền");
        } catch (Exception e) {
            log.error("Error toggling status permission: {}", e.getMessage());
            return Utils.createResponse(ResponseCode.ERROR);
        }
    }

    @Override
    public Response delete(Long id) {
        try {
            DatabaseUtils.deleteEntity(id, permissionRepository);
            return Utils.createResponse(ResponseCode.SUCCESS);
        } catch (EntityNotFoundException e) {
            log.error("Permission not found for ID {}: {}", id, e.getMessage());
            return Utils.createResponse(ResponseCode.ERROR, "Không thể tải thông tin quyền");
        } catch (Exception e) {
            log.error("Error deleting permission: {}", e.getMessage());
            return Utils.createResponse(ResponseCode.ERROR);
        }
    }

    @Override
    public Response getListOfPermissions(Map<String, String> filters) {
        try {
            String search = Utils.getSearch(filters);
            String module = JsonUtils.convert(filters.get("module"), String.class);
            Map<String, Object> result = DatabaseUtils.getList(filters, pageable -> permissionRepository.findAll(search, module, pageable));
            return Utils.createResponse(ResponseCode.SUCCESS, result);
        } catch (Exception e) {
            log.error("Error fetching permissions: {}", e.getMessage());
            return Utils.createResponse(ResponseCode.ERROR);
        }
    }

    @Override
    public Response getAll() {
        try {
            List<Permission> permissions = permissionRepository.findAll();
            return Utils.createResponse(ResponseCode.SUCCESS, permissions);
        } catch (Exception e) {
            log.error("Error fetching permissions all: {}", e.getMessage());
            return Utils.createResponse(ResponseCode.ERROR);
        }
    }

    @Override
    public Response update(PermissionDto permissionDto) {
        try {
            Permission permission = permissionDto.getEntity();
            DatabaseUtils.updateEntity(permission, permissionRepository);
            return Utils.createResponse(ResponseCode.SUCCESS, "Cập nhật quyền thành công");
        } catch (Exception e) {
            log.error("Error editing permission: {}", e.getMessage());
            return Utils.createResponse(ResponseCode.ERROR, "Cập nhật quyền thất bại");
        }
    }

    @Override
    public Response create(PermissionDto permissionDto) {
        try {
            Permission permission = permissionDto.getEntity();
            DatabaseUtils.createEntity(permission, permissionRepository);
            return Utils.createResponse(ResponseCode.SUCCESS, "Thêm quyền mới thành công");
        } catch (Exception e) {
            log.error("Error adding permission: {}", e.getMessage());
            return Utils.createResponse(ResponseCode.ERROR, "Thêm quyền mới thất bại");
        }
    }
}
