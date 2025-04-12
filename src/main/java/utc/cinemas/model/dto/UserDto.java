package utc.cinemas.model.dto;

import lombok.Data;
import org.springframework.beans.BeanUtils;
import utc.cinemas.model.entity.User;

import java.sql.Timestamp;

@Data
public class UserDto {
    private Long id;
    private String name;
    private String username;
    private String password;
    private String confirmPassword;
    private String email;
    private String phoneNumber;
    private Integer role;
    private Long createdUser;
    private Long modifiedUser;
    private Timestamp createdDate;
    private Timestamp modifiedDate;
    private Integer status;

    public User getEntity() {
        User user = new User();
        if (password != null && !password.equals(confirmPassword)) return null;
        BeanUtils.copyProperties(this, user, "confirmPassword");
        return user;
    }

    public static UserDto getDto(User user) {
        UserDto dto = new UserDto();
        BeanUtils.copyProperties(user, dto, "password");
        return dto;
    }
}