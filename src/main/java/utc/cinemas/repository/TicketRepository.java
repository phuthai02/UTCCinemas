package utc.cinemas.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import utc.cinemas.model.dto.ReportResponse;
import utc.cinemas.model.dto.TicketDto;
import utc.cinemas.model.entity.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    void deleteAllByShowtimeId(Long showtimeId);

    @Query("SELECT new utc.cinemas.model.dto.TicketDto(m.title, c.name, r.name, s.seatNumber, st.price, " +
            "CASE WHEN t.modifiedDate != t.createdDate THEN t.modifiedDate ELSE NULL END, " +
            "u.name, t.createdDate, t.createdUser, t.status, st.startTime) " +
            "FROM Ticket t " +
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

    @Query("SELECT new utc.cinemas.model.dto.ReportResponse(" +
            "COUNT(DISTINCT t.id), " +
            "SUM(CASE WHEN t.id IS NOT NULL AND t.status = 0 THEN st.price ELSE 0 END), " +
            "COUNT(DISTINCT CASE WHEN t.status = 0 THEN t.id ELSE NULL END), " +
            "COUNT(DISTINCT st.id)) " +
            "FROM Showtime st " +
            "LEFT JOIN Ticket t ON st.id = t.showtimeId " +
            "LEFT JOIN Room r ON st.roomId = r.id " +
            "LEFT JOIN Cinema c ON r.cinemaId = c.id " +
            "WHERE (:cinemaId = -1 OR c.id = :cinemaId)")
    ReportResponse getReportTotalByCinemaId(Long cinemaId);
}
