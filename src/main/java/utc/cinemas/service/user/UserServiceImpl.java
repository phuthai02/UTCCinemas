package utc.cinemas.service.user;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import utc.cinemas.model.dto.Response;
import utc.cinemas.model.dto.ResponseCode;
import utc.cinemas.model.dto.UserDto;
import utc.cinemas.model.entity.User;
import utc.cinemas.repository.UserRepository;
import utc.cinemas.util.Constants;
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
    public Response getListOfCustomers(Map<String, String> filters) {
        try {
            String search = Utils.getSearch(filters);
            Map<String, Object> result = DatabaseUtils.getList(filters, pageable -> userRepository.findAllStaffs("%" + search + "%", 0, pageable));
            return Utils.createResponse(ResponseCode.SUCCESS, result);
        } catch (Exception e) {
            log.error("Error fetching users: {}", e.getMessage());
            return Utils.createResponse(ResponseCode.ERROR);
        }
    }

    @Override
    public Response getListOfStaffs(Map<String, String> filters) {
        try {
            String search = Utils.getSearch(filters);
            Integer role = JsonUtils.convert(filters.get("role"), Integer.class);
            Map<String, Object> result = DatabaseUtils.getList(filters, pageable -> userRepository.findAllStaffs("%" + search + "%", role, pageable));
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
            user.setPassword(null);
            return Utils.createResponse(ResponseCode.SUCCESS, UserDto.getDto(user));
        } catch (Exception e) {
            log.error("Error getting user: {}", e.getMessage());
            return Utils.createResponse(ResponseCode.ERROR, "Không thể tải thông tin người dùng");
        }
    }

    @Override
    public Response getAll() {
        try {
            List<User> users = userRepository.findAll();
            users.forEach(user -> user.setPassword(null));
            return Utils.createResponse(ResponseCode.SUCCESS, users);
        } catch (Exception e) {
            log.error("Error fetching users all: {}", e.getMessage());
            return Utils.createResponse(ResponseCode.ERROR);
        }
    }

    @Override
    public Response createStaffs(UserDto userDto) {
        try {
            User user = userDto.getEntity();
            DatabaseUtils.createEntity(user, userRepository);
            return Utils.createResponse(ResponseCode.SUCCESS, "Thêm nhân viên mới thành công");
        } catch (Exception e) {
            log.error("Error adding staff: {}", e.getMessage());
            return Utils.createResponse(ResponseCode.ERROR, "Thêm nhân viên mới thất bại");
        }
    }

    @Override
    public Response createCustomers(UserDto userDto) {
        try {
            User user = userDto.getEntity();
            user.setRole(Constants.ROLE_CUSTOMER);
            DatabaseUtils.createEntity(user, userRepository);
            return Utils.createResponse(ResponseCode.SUCCESS, "Thêm khách hàng mới thành công");
        } catch (Exception e) {
            log.error("Error adding customers: {}", e.getMessage());
            return Utils.createResponse(ResponseCode.ERROR, "Thêm khách hàng mới thất bại");
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

    @Override
    public Response toggleStatus(Long id) {
        try {
            DatabaseUtils.toggleStatus(id, userRepository);
            return Utils.createResponse(ResponseCode.SUCCESS);
        } catch (EntityNotFoundException e) {
            log.error("User not found for ID {}: {}", id, e.getMessage());
            return Utils.createResponse(ResponseCode.ERROR, "Không thể tải thông tin người dùng");
        } catch (Exception e) {
            log.error("Error toggling status user: {}", e.getMessage());
            return Utils.createResponse(ResponseCode.ERROR);
        }
    }

    @Override
    public Response delete(Long id) {
        try {
            DatabaseUtils.deleteEntity(id, userRepository);
            return Utils.createResponse(ResponseCode.SUCCESS);
        } catch (EntityNotFoundException e) {
            log.error("User not found for ID {}: {}", id, e.getMessage());
            return Utils.createResponse(ResponseCode.ERROR, "Không thể tải thông tin người dùng");
        } catch (Exception e) {
            log.error("Error deleting user: {}", e.getMessage());
            return Utils.createResponse(ResponseCode.ERROR);
        }
    }
}
