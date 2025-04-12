package utc.cinemas.service.user;

import utc.cinemas.model.dto.Response;
import utc.cinemas.model.dto.UserDto;

import java.util.Map;

public interface UserService {
    Response getDirectors();
    Response getListOfUsers(Map<String, String> filters);
    Response getUserById(Long id);
    Response create(UserDto userDto);
    Response update(UserDto userDto);
}
