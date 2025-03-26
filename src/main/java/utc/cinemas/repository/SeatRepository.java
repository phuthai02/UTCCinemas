package utc.cinemas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import utc.cinemas.model.entity.Seat;

public interface SeatRepository extends JpaRepository<Seat, Long> {
}
