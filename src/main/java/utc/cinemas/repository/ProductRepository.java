package utc.cinemas.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import utc.cinemas.model.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p WHERE (p.productName LIKE :search OR p.description LIKE :search) AND (:cinemaId = -1 OR p.cinemaId = :cinemaId) AND p.display = 1")
    Page<Product> findAll(String search, Long cinemaId, Pageable pageable);
}
