package utc.cinemas.util;

import org.springframework.data.jpa.repository.JpaRepository;
import utc.cinemas.model.dto.Response;

import java.lang.reflect.Field;
import java.sql.Timestamp;

public class DatabaseUtils {

    public static <T, R extends JpaRepository<T, ?>> void createEntity(T entity, R repository) throws Exception {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        Long userId = AuthUtils.getUserId();
        setField(entity, "createdDate", now);
        setField(entity, "createdUser", userId);
        setField(entity, "modifiedDate", now);
        setField(entity, "modifiedUser", userId);
        setField(entity, "status", Constants.STATUS_ACTIVE);
        repository.save(entity);
    }

    private static <T> void setField(T entity, String fieldName, Object value) throws Exception {
        Field field = entity.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(entity, value);
    }
}
