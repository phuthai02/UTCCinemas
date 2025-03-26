package utc.cinemas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import utc.cinemas.model.entity.Showtime;

public interface ShowtimeRepository extends JpaRepository<Showtime, Long> {
}
