package utc.cinemas.service.user;

import utc.cinemas.model.dto.AuthRequest;
import utc.cinemas.model.dto.Response;
import utc.cinemas.model.entity.User;

import java.util.List;

public interface UserService {
    Response registerUser(AuthRequest user, Integer role);
    Response getDirectors();
}
