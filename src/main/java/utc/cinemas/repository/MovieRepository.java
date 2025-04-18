package utc.cinemas.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import utc.cinemas.model.entity.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    @Query("SELECT m FROM Movie m WHERE (m.title LIKE :search OR m.description LIKE :search OR m.genre LIKE :search) AND (:status = -1 OR m.status = :status)")
    Page<Movie> findAll(String search, Integer status, Pageable pageable);
}
