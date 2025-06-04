package utc.cinemas.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import utc.cinemas.model.entity.Equipment;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
    @Query("SELECT e FROM Equipment e WHERE (e.equipmentName LIKE :search OR e.description LIKE :search) AND (:equipmentType = '-1' OR e.equipmentType = :equipmentType)")
    Page<Equipment> findAll(String search, String equipmentType, Pageable pageable);
}
