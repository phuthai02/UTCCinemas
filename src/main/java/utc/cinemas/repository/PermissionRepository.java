package utc.cinemas.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import utc.cinemas.model.entity.Permission;

import java.util.List;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
    Permission findByIdAndStatus(Long id, Integer status);
    List<Permission> findByStatusAndDisplay(Integer status, Integer display);
    List<Permission> findByStatus(Integer statusActive);
    @Query("SELECT p FROM Permission p WHERE (p.permissionName LIKE :search OR p.description LIKE :search OR p.permissionCode LIKE :search) AND (:module = '-1' OR p.module = :module)")
    Page<Permission> findAll(String search, String module, Pageable pageable);
}
