package utc.cinemas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import utc.cinemas.model.entity.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
