package utc.cinemas.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import utc.cinemas.model.entity.Room;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {
    @Query("SELECT r FROM Room r WHERE (r.name LIKE :search) AND (:cinemaId = -1 OR r.cinemaId = :cinemaId) AND r.display = 1")
    Page<Room> findAll(String search, Long cinemaId, Pageable pageable);
    List<Room> findAllByCinemaId(Long cinemaId);
}
