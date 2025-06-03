package utc.cinemas.service.userpermisstion;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utc.cinemas.model.entity.Permission;
import utc.cinemas.model.entity.UserPermission;
import utc.cinemas.repository.PermissionRepository;
import utc.cinemas.repository.UserPermissionRepository;
import utc.cinemas.util.Constants;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserPermissionServiceImpl implements UserPermissionService {

    @Autowired
    private UserPermissionRepository userPermissionRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public List<String> getUserPermissions(Long userId) {
        List<UserPermission> userPermissions = userPermissionRepository.findByUserIdAndStatus(userId, Constants.STATUS_ACTIVE);

        return userPermissions.stream()
                .map(up -> {
                    Permission permission = permissionRepository.findByIdAndStatus(up.getPermissionId(), Constants.STATUS_ACTIVE);
                    return permission != null ? permission.getPermissionCode() : null;
                })
                .filter(code -> code != null)
                .collect(Collectors.toList());
    }

    @Override
    public List<Permission> getAllActivePermissions() {
        return permissionRepository.findByStatusAndDisplay(Constants.STATUS_ACTIVE, Constants.DISPLAY_VISIBLE);
    }

    @Override
    public List<String> getAllPermissionCodes() {
        List<Permission> allPermissions = permissionRepository.findByStatus(Constants.STATUS_ACTIVE);

        return allPermissions.stream()
                .map(Permission::getPermissionCode)
                .filter(code -> code != null && !code.trim().isEmpty())
                .collect(Collectors.toList());
    }
}
