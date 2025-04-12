package utc.cinemas.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import utc.cinemas.model.entity.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {
    @Query("SELECT c FROM Room c WHERE c.name LIKE ?1")
    Page<Room> findAll(String search, Pageable pageable);
}
