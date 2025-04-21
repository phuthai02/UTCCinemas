package utc.cinemas.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import utc.cinemas.model.entity.Seat;

public interface SeatRepository extends JpaRepository<Seat, Long> {
    @Query("SELECT s FROM Seat s " +
            "LEFT JOIN Room r ON s.roomId = r.id " +
            "LEFT JOIN Cinema c ON r.cinemaId = c.id " +
            "WHERE (s.seatNumber LIKE :search) " +
            "AND (:cinemaId = -1 OR c.id = :cinemaId) " +
            "AND (:roomId = -1 OR s.roomId = :roomId)")
    Page<Seat> findAll(String search, Long cinemaId, Long roomId, Pageable pageable);
}
