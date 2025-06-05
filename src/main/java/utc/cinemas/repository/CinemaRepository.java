package utc.cinemas.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import utc.cinemas.model.entity.Cinema;

import java.util.List;

public interface CinemaRepository extends JpaRepository<Cinema, Long> {
    @Query("SELECT c FROM Cinema c WHERE (c.name LIKE :search OR c.location LIKE :search) AND (:directorId = -1 OR c.cinemaDirector = :directorId) AND c.display = 1")
    Page<Cinema> findAll(String search, Long directorId, Pageable pageable);
}
