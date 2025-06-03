package utc.cinemas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import utc.cinemas.model.entity.Permission;

import java.util.List;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
    Permission findByIdAndStatus(Long id, Integer status);
    List<Permission> findByStatusAndDisplay(Integer status, Integer display);
    List<Permission> findByStatus(Integer statusActive);
}
