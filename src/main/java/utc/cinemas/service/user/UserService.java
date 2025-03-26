package utc.cinemas.service.user;

import utc.cinemas.model.dto.AuthRequest;
import utc.cinemas.model.dto.Response;

public interface UserService {
    Response registerUser(AuthRequest user, Integer role);
}
