package utc.cinemas.service.user;

import utc.cinemas.model.dto.Response;
import utc.cinemas.model.dto.UserDto;

import java.util.Map;

public interface UserService {
    Response getListOfCustomers(Map<String, String> filters);
    Response getListOfStaffs(Map<String, String> filters);
    Response getUserById(Long id);
    Response getAll();
    Response createStaffs(UserDto userDto);
    Response createCustomers(UserDto userDto);
    Response update(UserDto userDto);
    Response delete(Long id);
    Response toggleStatus(Long id);
}
