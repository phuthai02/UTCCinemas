package utc.cinemas.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import utc.cinemas.model.entity.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    @Query("SELECT m FROM Movie m WHERE m.title LIKE ?1 OR m.description LIKE ?1 OR m.genre LIKE ?1")
    Page<Movie> findAll(String search, Pageable pageable);
}
