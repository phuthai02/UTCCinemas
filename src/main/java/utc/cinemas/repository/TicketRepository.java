package utc.cinemas.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import utc.cinemas.model.dto.TicketDto;
import utc.cinemas.model.entity.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    void deleteAllByShowtimeId(Long showtimeId);

        @Query("SELECT new utc.cinemas.model.dto.TicketDto(m.title, c.name, r.name, s.seatNumber, u.name, t.createdDate, t.createdUser, t.status) FROM Ticket t " +
                "LEFT JOIN Showtime st ON t.showtimeId = st.id " +
                "LEFT JOIN Room r ON st.roomId = r.id " +
                "LEFT JOIN Cinema c ON r.cinemaId = c.id " +
                "LEFT JOIN Movie m ON st.movieId = m.id " +
                "LEFT JOIN User u ON t.userId = u.id " +
                "LEFT JOIN Seat s ON t.seatId = s.id " +
                "WHERE (:cinemaId = -1 OR c.id = :cinemaId) " +
                "AND (:roomId = -1 OR st.roomId = :roomId) " +
                "AND (:movieId = -1 OR st.movieId = :movieId)")
    Page<TicketDto> findAll(String search, Long cinemaId, Long roomId, Long movieId, Pageable pageable);
}
