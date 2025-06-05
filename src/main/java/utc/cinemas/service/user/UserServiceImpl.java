package utc.cinemas.service.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import utc.cinemas.model.dto.Response;
import utc.cinemas.model.dto.ResponseCode;
import utc.cinemas.model.dto.UserDto;
import utc.cinemas.model.entity.User;
import utc.cinemas.repository.UserRepository;
import utc.cinemas.util.DatabaseUtils;
import utc.cinemas.util.JsonUtils;
import utc.cinemas.util.Utils;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public Response getListOfConsumers(Map<String, String> filters) {
        return null;
    }

    @Override
    public Response getListOfStaffs(Map<String, String> filters) {
        return null;
    }

    @Override
    public Response getUserById(Long id) {
        return null;
    }

    @Override
    public Response getAll() {
        return null;
    }

    @Override
    public Response createStaffs(UserDto userDto) {
        return null;
    }

    @Override
    public Response createConsumers(UserDto userDto) {
        return null;
    }

    @Override
    public Response update(UserDto userDto) {
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
