package utc.cinemas.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import utc.cinemas.model.entity.Equipment;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
    @Query("SELECT e FROM Equipment e " +
            "LEFT JOIN Room r ON e.roomId = r.id " +
            "LEFT JOIN Cinema c ON r.cinemaId = c.id " +
            "WHERE (e.equipmentName LIKE :search OR e.description LIKE :search) " +
            "AND (:cinemaId = -1 OR c.id = :cinemaId) " +
            "AND (:roomId = -1 OR e.roomId = :roomId) " +
            "AND e.display = 1")
    Page<Equipment> findAll(String search, Long cinemaId, Long roomId, Pageable pageable);
}
