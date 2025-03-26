package utc.cinemas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import utc.cinemas.model.entity.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
