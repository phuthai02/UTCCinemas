package utc.cinemas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import utc.cinemas.model.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
