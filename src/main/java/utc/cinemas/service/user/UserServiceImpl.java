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

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Response getDirectors() {
        Response response = Utils.createResponse(ResponseCode.ERROR);
        try {
            List<User> directors = userRepository.findAll();
            response = Utils.createResponse(ResponseCode.SUCCESS, directors);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return response;
    }

    @Override
    public Response getListOfUsers(Map<String, String> filters) {
        try {
            String search = JsonUtils.convert(filters.get("search"), String.class).trim();
            Integer role = JsonUtils.convert(filters.get("role"), Integer.class);
            Map<String, Object> result = DatabaseUtils.getList(filters, pageable -> userRepository.findAll("%" + search + "%", role, pageable));
            return Utils.createResponse(ResponseCode.SUCCESS, result);
        } catch (Exception e) {
            log.error("Error fetching users: {}", e.getMessage());
            return Utils.createResponse(ResponseCode.ERROR);
        }
    }

    @Override
    public Response getUserById(Long id) {
        try {
            User user = userRepository.findById(id).orElse(null);
            return Utils.createResponse(ResponseCode.SUCCESS, UserDto.getDto(user));
        } catch (Exception e) {
            log.error("Error getting user: {}", e.getMessage());
            return Utils.createResponse(ResponseCode.ERROR, "Tìm kiếm người thất bại");
        }
    }

    @Override
    public Response create(UserDto userDto) {
        try {
            User user = userDto.getEntity();
            if (user == null) {
                return Utils.createResponse(ResponseCode.ERROR, "Mật khẩu không trùng khớp vui lòng kiểm tra lại");
            }
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
            DatabaseUtils.createEntity(user, userRepository);
            return Utils.createResponse(ResponseCode.SUCCESS, "Thêm người dùng mới thành công");
        } catch (Exception e) {
            log.error("Error adding user: {}", e.getMessage());
            return Utils.createResponse(ResponseCode.ERROR, "Thêm người dùng mới thất bại");
        }
    }

    @Override
    public Response update(UserDto userDto) {
        try {
            User user = userDto.getEntity();
            if (user == null) {
                return Utils.createResponse(ResponseCode.ERROR, "Mật khẩu không trùng khớp vui lòng kiểm tra lại");
            }
            if (user.getPassword() != null) user.setPassword(passwordEncoder.encode(userDto.getPassword()));
            DatabaseUtils.updateEntity(user, userRepository);
            return Utils.createResponse(ResponseCode.SUCCESS, "Cập nhật người dùng thành công");
        } catch (Exception e) {
            log.error("Error editing user: {}", e.getMessage());
            return Utils.createResponse(ResponseCode.ERROR, "Cập nhật người dùng thất bại");
        }
    }
}
