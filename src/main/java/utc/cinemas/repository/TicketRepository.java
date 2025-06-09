package utc.cinemas.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import utc.cinemas.model.dto.TicketDto;
import utc.cinemas.model.entity.Ticket;

import java.sql.Timestamp;
import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    @Query("SELECT new utc.cinemas.model.dto.TicketDto(t.id, m.title, c.name, r.name, s.seatNumber, t.price, " +
            "CASE WHEN t.modifiedDate != t.createdDate THEN t.modifiedDate ELSE NULL END, " +
            "u.name, t.createdDate, t.createdUser, t.status, st.startTime, " +
            "CASE WHEN (SELECT COUNT(t2) FROM Ticket t2 WHERE t2.showtimeId = t.showtimeId AND t2.seatId = t.seatId AND t.display = 1) > 1 THEN 1 ELSE 0 END) " +
            "FROM Ticket t " +
            "LEFT JOIN Showtime st ON t.showtimeId = st.id " +
            "LEFT JOIN Room r ON st.roomId = r.id " +
            "LEFT JOIN Cinema c ON r.cinemaId = c.id " +
            "LEFT JOIN Movie m ON st.movieId = m.id " +
            "LEFT JOIN User u ON t.userId = u.id " +
            "LEFT JOIN Seat s ON t.seatId = s.id " +
            "WHERE (:cinemaId = -1 OR c.id = :cinemaId) " +
            "AND (:roomId = -1 OR st.roomId = :roomId) " +
            "AND (:movieId = -1 OR st.movieId = :movieId) " +
            "AND (:status = -1 OR t.status = :status)")
    Page<TicketDto> findAllWithDuplicateFlag(String search, Long cinemaId, Long roomId, Long movieId, Integer status, Pageable pageable);

    // Báo cáo theo chi nhánh
    @Query("SELECT c.name, COUNT(t.id), COALESCE(SUM(t.price), 0) " +
            "FROM Cinema c " +
            "LEFT JOIN Room r ON c.id = r.cinemaId " +
            "LEFT JOIN Showtime s ON r.id = s.roomId " +
            "LEFT JOIN Ticket t ON s.id = t.showtimeId AND t.status = 0 " +
            "WHERE c.display = 1 " +
            "AND (:cinemaId = -1 OR c.id = :cinemaId) " +
            "AND (:startDate IS NULL OR t.createdDate >= :startDate) " +
            "AND (:endDate IS NULL OR t.createdDate <= :endDate) " +
            "GROUP BY c.id, c.name " +
            "ORDER BY COALESCE(SUM(t.price), 0) DESC")
    List<Object[]> getCinemaReport(@Param("cinemaId") Long cinemaId,
                                   @Param("startDate") Timestamp startDate,
                                   @Param("endDate") Timestamp endDate);

    // Báo cáo theo phòng chiếu
    @Query("SELECT CONCAT(c.name, CONCAT(' - ', r.name)), COUNT(t.id), COALESCE(SUM(t.price), 0) " +
            "FROM Room r " +
            "JOIN Cinema c ON r.cinemaId = c.id " +
            "LEFT JOIN Showtime s ON r.id = s.roomId " +
            "LEFT JOIN Ticket t ON s.id = t.showtimeId AND t.status = 0 " +
            "WHERE r.display = 1 AND c.display = 1 " +
            "AND (:roomId = -1 OR r.id = :roomId) " +
            "AND (:startDate IS NULL OR t.createdDate >= :startDate) " +
            "AND (:endDate IS NULL OR t.createdDate <= :endDate) " +
            "GROUP BY r.id, c.name, r.name " +
            "ORDER BY COALESCE(SUM(t.price), 0) DESC")
    List<Object[]> getRoomReport(@Param("roomId") Long roomId,
                                 @Param("startDate") Timestamp startDate,
                                 @Param("endDate") Timestamp endDate);

    // Báo cáo theo phim
    @Query("SELECT m.title, COUNT(t.id), COALESCE(SUM(t.price), 0) " +
            "FROM Movie m " +
            "LEFT JOIN Showtime s ON m.id = s.movieId " +
            "LEFT JOIN Ticket t ON s.id = t.showtimeId AND t.status = 0 " +
            "WHERE m.display = 1 " +
            "AND (:movieId = -1 OR m.id = :movieId) " +
            "AND (:startDate IS NULL OR t.createdDate >= :startDate) " +
            "AND (:endDate IS NULL OR t.createdDate <= :endDate) " +
            "GROUP BY m.id, m.title " +
            "ORDER BY COALESCE(SUM(t.price), 0) DESC")
    List<Object[]> getMovieReport(@Param("movieId") Long movieId,
                                  @Param("startDate") Timestamp startDate,
                                  @Param("endDate") Timestamp endDate);

    // Tổng số vé bán ra - All records
    @Query("SELECT COUNT(t.id) " +
            "FROM Ticket t " +
            "JOIN Showtime s ON t.showtimeId = s.id " +
            "AND (:startDate IS NULL OR t.createdDate >= :startDate) " +
            "AND (:endDate IS NULL OR t.createdDate <= :endDate)")
    Long getTotalTicketsAll(@Param("startDate") Timestamp startDate,
                            @Param("endDate") Timestamp endDate);

    // Tổng số bán ra vé theo cinema
    @Query("SELECT COUNT(t.id) " +
            "FROM Ticket t " +
            "JOIN Showtime s ON t.showtimeId = s.id " +
            "JOIN Room r ON s.roomId = r.id " +
            "AND (r.cinemaId = :entityId OR :entityId = -1) " +
            "AND (:startDate IS NULL OR t.createdDate >= :startDate) " +
            "AND (:endDate IS NULL OR t.createdDate <= :endDate)")
    Long getTotalTicketsByCinema(@Param("entityId") Long entityId,
                                 @Param("startDate") Timestamp startDate,
                                 @Param("endDate") Timestamp endDate);

    // Tổng số bán ra vé theo room
    @Query("SELECT COUNT(t.id) " +
            "FROM Ticket t " +
            "JOIN Showtime s ON t.showtimeId = s.id " +
            "AND (s.roomId = :entityId OR :entityId = -1 ) " +
            "AND (:startDate IS NULL OR t.createdDate >= :startDate) " +
            "AND (:endDate IS NULL OR t.createdDate <= :endDate)")
    Long getTotalTicketsByRoom(@Param("entityId") Long entityId,
                               @Param("startDate") Timestamp startDate,
                               @Param("endDate") Timestamp endDate);

    // Tổng số vé bán ra theo movie
    @Query("SELECT COUNT(t.id) " +
            "FROM Ticket t " +
            "JOIN Showtime s ON t.showtimeId = s.id " +
            "AND (s.movieId = :entityId OR :entityId = -1) " +
            "AND (:startDate IS NULL OR t.createdDate >= :startDate) " +
            "AND (:endDate IS NULL OR t.createdDate <= :endDate)")
    Long getTotalTicketsByMovie(@Param("entityId") Long entityId,
                                @Param("startDate") Timestamp startDate,
                                @Param("endDate") Timestamp endDate);

    // Tổng số vé đã bán - All records
    @Query("SELECT COUNT(t.id) " +
            "FROM Ticket t " +
            "JOIN Showtime s ON t.showtimeId = s.id " +
            "WHERE t.status = 0 " +
            "AND (:startDate IS NULL OR t.createdDate >= :startDate) " +
            "AND (:endDate IS NULL OR t.createdDate <= :endDate)")
    Long getTotalViewsAll(@Param("startDate") Timestamp startDate,
                            @Param("endDate") Timestamp endDate);

    // Tổng số vé đã bán theo cinema
    @Query("SELECT COUNT(t.id) " +
            "FROM Ticket t " +
            "JOIN Showtime s ON t.showtimeId = s.id " +
            "JOIN Room r ON s.roomId = r.id " +
            "WHERE t.status = 0 " +
            "AND (r.cinemaId = :entityId OR :entityId = -1) " +
            "AND (:startDate IS NULL OR t.createdDate >= :startDate) " +
            "AND (:endDate IS NULL OR t.createdDate <= :endDate)")
    Long getTotalViewsByCinema(@Param("entityId") Long entityId,
                                 @Param("startDate") Timestamp startDate,
                                 @Param("endDate") Timestamp endDate);

    // Tổng số vé đã bán theo room
    @Query("SELECT COUNT(t.id) " +
            "FROM Ticket t " +
            "JOIN Showtime s ON t.showtimeId = s.id " +
            "WHERE t.status = 0 " +
            "AND (s.roomId = :entityId OR :entityId = -1 ) " +
            "AND (:startDate IS NULL OR t.createdDate >= :startDate) " +
            "AND (:endDate IS NULL OR t.createdDate <= :endDate)")
    Long getTotalViewsByRoom(@Param("entityId") Long entityId,
                               @Param("startDate") Timestamp startDate,
                               @Param("endDate") Timestamp endDate);

    // Tổng số vé đã bán theo movie
    @Query("SELECT COUNT(t.id) " +
            "FROM Ticket t " +
            "JOIN Showtime s ON t.showtimeId = s.id " +
            "WHERE t.status = 0 " +
            "AND (s.movieId = :entityId OR :entityId = -1) " +
            "AND (:startDate IS NULL OR t.createdDate >= :startDate) " +
            "AND (:endDate IS NULL OR t.createdDate <= :endDate)")
    Long getTotalViewsByMovie(@Param("entityId") Long entityId,
                                @Param("startDate") Timestamp startDate,
                                @Param("endDate") Timestamp endDate);

    // Tổng doanh thu - All records
    @Query("SELECT COALESCE(SUM(t.price), 0) " +
            "FROM Ticket t " +
            "JOIN Showtime s ON t.showtimeId = s.id " +
            "WHERE t.status = 0 " +
            "AND (:startDate IS NULL OR t.createdDate >= :startDate) " +
            "AND (:endDate IS NULL OR t.createdDate <= :endDate)")
    Long getTotalRevenueAll(@Param("startDate") Timestamp startDate,
                            @Param("endDate") Timestamp endDate);

    // Tổng doanh thu theo cinema
    @Query("SELECT COALESCE(SUM(t.price), 0) " +
            "FROM Ticket t " +
            "JOIN Showtime s ON t.showtimeId = s.id " +
            "JOIN Room r ON s.roomId = r.id " +
            "WHERE t.status = 0 " +
            "AND (r.cinemaId = :entityId  OR :entityId = -1) " +
            "AND (:startDate IS NULL OR t.createdDate >= :startDate) " +
            "AND (:endDate IS NULL OR t.createdDate <= :endDate)")
    Long getTotalRevenueByCinema(@Param("entityId") Long entityId,
                                 @Param("startDate") Timestamp startDate,
                                 @Param("endDate") Timestamp endDate);

    // Tổng doanh thu theo room
    @Query("SELECT COALESCE(SUM(t.price), 0) " +
            "FROM Ticket t " +
            "JOIN Showtime s ON t.showtimeId = s.id " +
            "WHERE t.status = 0 " +
            "AND (s.roomId = :entityId  OR :entityId = -1) " +
            "AND (:startDate IS NULL OR t.createdDate >= :startDate) " +
            "AND (:endDate IS NULL OR t.createdDate <= :endDate)")
    Long getTotalRevenueByRoom(@Param("entityId") Long entityId,
                               @Param("startDate") Timestamp startDate,
                               @Param("endDate") Timestamp endDate);

    // Tổng doanh thu theo movie
    @Query("SELECT COALESCE(SUM(t.price), 0) " +
            "FROM Ticket t " +
            "JOIN Showtime s ON t.showtimeId = s.id " +
            "WHERE t.status = 0 " +
            "AND (s.movieId = :entityId OR :entityId = -1) " +
            "AND (:startDate IS NULL OR t.createdDate >= :startDate) " +
            "AND (:endDate IS NULL OR t.createdDate <= :endDate)")
    Long getTotalRevenueByMovie(@Param("entityId") Long entityId,
                                @Param("startDate") Timestamp startDate,
                                @Param("endDate") Timestamp endDate);

    // Tổng số suất chiếu - All records
    @Query("SELECT COUNT(DISTINCT s.id) " +
            "FROM Showtime s " +
            "WHERE s.display = 1 " +
            "AND (:startDate IS NULL OR s.startTime >= :startDate) " +
            "AND (:endDate IS NULL OR s.startTime <= :endDate)")
    Long getTotalShowtimesAll(@Param("startDate") Timestamp startDate,
                              @Param("endDate") Timestamp endDate);

    // Tổng số suất chiếu theo cinema
    @Query("SELECT COUNT(DISTINCT s.id) " +
            "FROM Showtime s " +
            "JOIN Room r ON s.roomId = r.id " +
            "WHERE s.display = 1 " +
            "AND (r.cinemaId = :entityId OR :entityId = -1) " +
            "AND (:startDate IS NULL OR s.startTime >= :startDate) " +
            "AND (:endDate IS NULL OR s.startTime <= :endDate)")
    Long getTotalShowtimesByCinema(@Param("entityId") Long entityId,
                                   @Param("startDate") Timestamp startDate,
                                   @Param("endDate") Timestamp endDate);

    // Tổng số suất chiếu theo room
    @Query("SELECT COUNT(DISTINCT s.id) " +
            "FROM Showtime s " +
            "WHERE s.display = 1 " +
            "AND (s.roomId = :entityId OR :entityId = -1) " +
            "AND (:startDate IS NULL OR s.startTime >= :startDate) " +
            "AND (:endDate IS NULL OR s.startTime <= :endDate)")
    Long getTotalShowtimesByRoom(@Param("entityId") Long entityId,
                                 @Param("startDate") Timestamp startDate,
                                 @Param("endDate") Timestamp endDate);

    // Tổng số suất chiếu theo movie
    @Query("SELECT COUNT(DISTINCT s.id) " +
            "FROM Showtime s " +
            "WHERE s.display = 1 " +
            "AND (s.movieId = :entityId OR :entityId = -1) " +
            "AND (:startDate IS NULL OR s.startTime >= :startDate) " +
            "AND (:endDate IS NULL OR s.startTime <= :endDate)")
    Long getTotalShowtimesByMovie(@Param("entityId") Long entityId,
                                  @Param("startDate") Timestamp startDate,
                                  @Param("endDate") Timestamp endDate);

    // Dữ liệu biểu đồ theo ngày - All records
    @Query(value = """
        SELECT TRUNC(t.created_date), NVL(SUM(t.price), 0)
        FROM tickets t
        INNER JOIN showtimes s ON t.showtime_id = s.id
        WHERE t.status = 0
            AND (:startDate IS NULL OR t.created_date >= :startDate)
            AND (:endDate IS NULL OR t.created_date <= :endDate)
        GROUP BY TRUNC(t.created_date)
        ORDER BY TRUNC(t.created_date)
        """, nativeQuery = true)
    List<Object[]> getChartDataAll(@Param("startDate") Timestamp startDate,
                                   @Param("endDate") Timestamp endDate);

    // Dữ liệu biểu đồ theo cinema
    @Query("SELECT c.name, COALESCE(SUM(t.price), 0) " +
            "FROM Ticket t " +
            "JOIN Showtime s ON t.showtimeId = s.id " +
            "JOIN Room r ON s.roomId = r.id " +
            "JOIN Cinema c ON r.cinemaId = c.id " +
            "WHERE t.status = 0 " +
            "AND (:entityId = -1 OR c.id = :entityId) " +
            "AND (:startDate IS NULL OR t.createdDate >= :startDate) " +
            "AND (:endDate IS NULL OR t.createdDate <= :endDate) " +
            "GROUP BY c.name " +
            "ORDER BY c.name")
    List<Object[]> getChartDataByCinema(@Param("entityId") Long entityId,
                                        @Param("startDate") Timestamp startDate,
                                        @Param("endDate") Timestamp endDate);

    // Dữ liệu biểu đồ theo room
    @Query(value = "SELECT r.name, NVL(SUM(t.price), 0) " +
            "FROM tickets t " +
            "INNER JOIN showtimes s ON t.showtime_id = s.id " +
            "INNER JOIN rooms r ON s.room_id = r.id " +
            "WHERE t.status = 0 " +
            "AND (r.id = :entityId OR :entityId = -1) " +
            "AND (:startDate IS NULL OR t.created_date >= :startDate) " +
            "AND (:endDate IS NULL OR t.created_date <= :endDate) " +
            "GROUP BY r.name " +
            "ORDER BY r.name",
            nativeQuery = true)
    List<Object[]> getChartDataByRoom(@Param("entityId") Long entityId,
                                      @Param("startDate") Timestamp startDate,
                                      @Param("endDate") Timestamp endDate);

    // Dữ liệu biểu đồ theo movie
    @Query(value = "SELECT m.title, NVL(SUM(t.price), 0) " +
            "FROM tickets t " +
            "INNER JOIN showtimes s ON t.showtime_id = s.id " +
            "INNER JOIN movies m ON s.movie_id = m.id " +
            "WHERE t.status = 0 " +
            "AND (m.id = :entityId OR :entityId = -1) " +
            "AND (:startDate IS NULL OR t.created_date >= :startDate) " +
            "AND (:endDate IS NULL OR t.created_date <= :endDate) " +
            "GROUP BY m.title " +
            "ORDER BY m.title",
            nativeQuery = true)
    List<Object[]> getChartDataByMovie(@Param("entityId") Long entityId,
                                       @Param("startDate") Timestamp startDate,
                                       @Param("endDate") Timestamp endDate);
}