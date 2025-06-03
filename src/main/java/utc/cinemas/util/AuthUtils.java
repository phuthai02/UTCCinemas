package utc.cinemas.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;
import java.util.Objects;

public class AuthUtils {
    private static final ThreadLocal<Long> userIdThreadLocal = new ThreadLocal<>();

    public static Long getUserId() {
        return userIdThreadLocal.get();
    }

    public static void setUserId(Long userId) {
        userIdThreadLocal.set(userId);
    }

    public static void clear() {
        userIdThreadLocal.remove();
    }

    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public static boolean isAuthenticated() {
        Authentication authentication = getAuthentication();
        return authentication != null && authentication.isAuthenticated()
                && !Objects.equals(authentication.getPrincipal(), "anonymousUser");
    }
}
