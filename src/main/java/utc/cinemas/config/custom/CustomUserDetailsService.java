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

import java.util.*;
import java.util.stream.Collectors;

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

        // Kiểm tra role hợp lệ
        if (user.getRole() == null || (!Objects.equals(Constants.ROLE_ADMIN, user.getRole()) && !Objects.equals(Constants.ROLE_DEVELOPER, user.getRole()))) {
            throw new UsernameNotFoundException("Chỉ admin mới được phép đăng nhập vào hệ thống");
        }

        Collection<GrantedAuthority> authorities = new HashSet<>();

        // Nếu là DEVELOPER thì cấp tất cả quyền
        if (Objects.equals(Constants.ROLE_DEVELOPER, user.getRole())) {
            List<String> allPermissions = userPermissionService.getAllPermissionCodes();
            authorities.addAll(allPermissions.stream()
                    .map(SimpleGrantedAuthority::new)
                    .toList());
        } else {
            // Nếu là ADMIN thường thì lấy quyền từ database
            List<String> permissions = userPermissionService.getUserPermissions(user.getId());
            authorities.addAll(permissions.stream()
                    .map(SimpleGrantedAuthority::new)
                    .toList());
        }

        return new CustomUserDetails(user, authorities);
    }
}