package utc.cinemas.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import utc.cinemas.model.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    @Query("SELECT u FROM User u WHERE (u.name LIKE :search OR u.username LIKE :search) AND u.display = 1 AND ((:role = -1 AND u.role <> 0) OR (:role <> -1 AND u.role = :role))")
    Page<User> findAllStaffs(@Param("search") String search, @Param("role") Integer role, Pageable pageable);
}
