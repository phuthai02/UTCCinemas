package utc.cinemas.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import utc.cinemas.model.entity.Cinema;

public interface CinemaRepository extends JpaRepository<Cinema, Long> {
    @Query("SELECT c FROM Cinema c WHERE c.name LIKE ?1 OR c.location LIKE ?1")
    Page<Cinema> findAll(String search, Pageable pageable);
}
