package utc.cinemas.config.custom;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import utc.cinemas.model.entity.User;
import utc.cinemas.repository.UserRepository;
import utc.cinemas.service.userpermisstion.UserPermissionService;
import utc.cinemas.util.Constants;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserPermissionService userPermissionService;

    public CustomUserDetailsService(UserRepository userRepository, UserPermissionService userPermissionService) {
        this.userRepository = userRepository;
        this.userPermissionService = userPermissionService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Không tìm thấy user với username: " + username));

        if (user.getRole() == null || Objects.equals(Constants.ROLE_USER, user.getRole())) {
            throw new UsernameNotFoundException("Người dùng không được phép đăng nhập vào hệ thống");
        }

        Collection<GrantedAuthority> authorities = new HashSet<>();

        List<String> permissions = userPermissionService.getUserPermissions(user.getId());
        if (permissions.isEmpty()) {
            permissions = Constants.getDefaultPermissionsForRole(user.getRole());
        }

        authorities.addAll(permissions.stream().map(SimpleGrantedAuthority::new).toList());

        return new CustomUserDetails(user, authorities);
    }
}