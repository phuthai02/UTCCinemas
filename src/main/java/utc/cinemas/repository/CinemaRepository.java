package utc.cinemas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import utc.cinemas.model.entity.Cinema;

public interface CinemaRepository extends JpaRepository<Cinema, Long> {
}
