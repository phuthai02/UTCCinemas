package utc.cinemas.service.userpermisstion;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utc.cinemas.model.dto.Response;
import utc.cinemas.model.dto.UserPermissionDto;
import utc.cinemas.repository.UserPermissionRepository;

import java.util.Map;

@Service
@Slf4j
public class UserPermissionServiceImpl implements UserPermissionService {

    @Autowired
    private UserPermissionRepository userPermissionRepository;

    @Override
    public Response getListOfUserPermissions(Map<String, String> filters) {
        return null;
    }

    @Override
    public Response getUserPermissionById(Long id) {
        return null;
    }

    @Override
    public Response getAll() {
        return null;
    }

    @Override
    public Response create(UserPermissionDto userPermissionDto) {
        return null;
    }

    @Override
    public Response update(UserPermissionDto userPermissionDto) {
        return null;
    }

    @Override
    public Response delete(Long id) {
        return null;
    }

    @Override
    public Response toggleStatus(Long id) {
        return null;
    }
}
