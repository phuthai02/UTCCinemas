package utc.cinemas.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import utc.cinemas.model.entity.Showtime;

public interface ShowtimeRepository extends JpaRepository<Showtime, Long> {
    @Query("SELECT s FROM Showtime s " +
            "LEFT JOIN Room r ON s.roomId = r.id " +
            "LEFT JOIN Cinema c ON r.cinemaId = c.id " +
            "LEFT JOIN Movie m ON s.movieId = m.id " +
            "WHERE (:cinemaId = -1 OR c.id = :cinemaId) " +
            "AND (:roomId = -1 OR s.roomId = :roomId) " +
            "AND (:movieId = -1 OR s.movieId = :movieId)")
    Page<Showtime> findAll(String search, Long cinemaId, Long roomId, Long movieId, Pageable pageable);
}
