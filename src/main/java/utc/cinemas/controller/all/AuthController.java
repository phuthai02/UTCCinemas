package utc.cinemas.controller.all;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import utc.cinemas.config.jwt.JwtService;
import utc.cinemas.model.dto.AuthRequest;
import utc.cinemas.model.dto.AuthResponse;
import utc.cinemas.model.dto.Response;
import utc.cinemas.model.entity.User;
import utc.cinemas.repository.UserRepository;
import utc.cinemas.service.user.UserService;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@Slf4j
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<Response> login(@RequestBody AuthRequest request) {
        log.info("Login request: {}", request);
        try {
            User user = userRepository.findByUsername(request.getUsername())
                    .orElseThrow(() -> new UsernameNotFoundException("Tài khoản chưa được đăng ký"));

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );

            String token = jwtService.generateToken(user.getUsername(), user.getName(), user.getRole());

            return buildResponse(200, "Đăng nhập thành công", new AuthResponse(token));
        } catch (UsernameNotFoundException ex) {
            return buildResponse(404, "Tài khoản chưa được đăng ký", null);
        } catch (org.springframework.security.authentication.BadCredentialsException ex) {
            return buildResponse(401, "Sai mật khẩu, vui lòng kiểm tra lại", null);
        } catch (DisabledException ex) {
            return buildResponse(403, "Tài khoản bị vô hiệu hóa", null);
        } catch (Exception ex) {
            log.error("Login failed: {}", ex.getMessage());
            return buildResponse(500, "Lỗi hệ thống, vui lòng thử lại sau", null);
        }
    }


    @GetMapping("/validate-token")
    public ResponseEntity<Response> validateToken(@RequestHeader("Authorization") String token) {
        String jwt = token.replaceFirst("^Bearer\\s+", "");
        boolean isValid = jwtService.validateTokenWithoutUserDetails(jwt);
        return isValid
                ? ResponseEntity.ok(new Response(200, "Token is valid", null))
                : ResponseEntity.status(401).body(new Response(401, "Invalid token", null));
    }

    @PostMapping("/check-token")
    public ResponseEntity<?> checkToken(@RequestHeader("Authorization") String token) {
        String jwt = token.replaceFirst("^Bearer\\s+", "");
        if (jwtService.isTokenExpired(jwt)) {
            return ResponseEntity.status(401).body(Map.of("valid", false, "message", "Token expired"));
        }
        String name = jwtService.extractName(jwt);
        return ResponseEntity.ok(Map.of("valid", true, "name", name));
    }

    @PostMapping("/logout")
    public ResponseEntity<Response> logout(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextHolder.clearContext();
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())) {
                    cookie.setValue("");
                    cookie.setPath("/");
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }
        }
        return ResponseEntity.ok(new Response(200, "Đăng xuất thành công",
                Map.of("redirectUrl", "/utc-cinemas/login")));
    }

    private ResponseEntity<Response> buildResponse(int status, String message, Object data) {
        return ResponseEntity.status(status).body(new Response(status, message, data));
    }
}