package utc.cinemas.service.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import utc.cinemas.model.dto.AuthRequest;
import utc.cinemas.model.dto.Response;
import utc.cinemas.model.dto.ResponseCode;
import utc.cinemas.model.entity.User;
import utc.cinemas.repository.UserRepository;
import utc.cinemas.util.Constants;
import utc.cinemas.util.Utils;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Response registerUser(AuthRequest authRequest, Integer role) {
        Response response = Utils.createResponse(ResponseCode.ERROR);
        try {
            User user = userRepository.findByUsername(authRequest.getUsername()).orElse(null);
            if (user != null) {
                log.info("Username already exists");
                response = Utils.createResponse(ResponseCode.USER_ALREADY_EXISTS);
                return response;
            }
            user = new User();
            user.setUsername(authRequest.getUsername());
            user.setPassword(passwordEncoder.encode(authRequest.getPassword()));
            user.setEmail(authRequest.getEmail());
            user.setPhoneNumber(authRequest.getPhoneNumber());
            user.setRole(role);
            userRepository.save(user);
            response = Utils.createResponse(ResponseCode.SUCCESS);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return response;
    }
}
