package utc.cinemas.util;

import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.Id;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

public class DatabaseUtils {

    public static <T> Map<String, Object> getList(
            Map<String, String> filters,
            Function<Pageable, Page<T>> fetchFunction
    ) {
        Pageable pageable = Utils.getPageable(filters);
        Page<T> resultPage = fetchFunction.apply(pageable);

        Map<String, Object> response = new HashMap<>();
        response.put("data", resultPage.getContent());
        response.put("totalPages", resultPage.getTotalPages());

        return response;
    }

    public static <T, R extends JpaRepository<T, Long>> void createEntity(T entity, R repository) throws Exception {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        Long userId = AuthUtils.getUserId();
        setField(entity, "createdDate", now);
        setField(entity, "createdUser", userId);
        setField(entity, "modifiedDate", now);
        setField(entity, "modifiedUser", userId);
        setField(entity, "status", Constants.STATUS_ACTIVE);
        setField(entity, "display", Constants.DISPLAY_VISIBLE);
        repository.save(entity);
    }

    private static <T> void setField(T entity, String fieldName, Object value) throws Exception {
        Field field = entity.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(entity, value);
    }

    public static <T, R extends JpaRepository<T, Long>> void updateEntity(T newEntity, R repository) throws Exception {
        Field idField = findIdField(newEntity.getClass());
        if (idField == null) {
            throw new IllegalArgumentException("No ID field found in entity");
        }

        idField.setAccessible(true);
        Long id = (Long) idField.get(newEntity);

        if (id == null) {
            throw new IllegalArgumentException("Entity ID cannot be null for update operation");
        }

        Optional<T> existingEntityOpt = repository.findById(id);
        if (existingEntityOpt.isEmpty()) {
            throw new EntityNotFoundException("Entity with ID " + id + " not found");
        }

        T existingEntity = existingEntityOpt.get();

        Field[] fields = newEntity.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Object newValue = field.get(newEntity);

            if (newValue != null && !field.equals(idField) &&
                    !field.getName().equals("createdDate") &&
                    !field.getName().equals("createdUser")) {
                field.set(existingEntity, newValue);
            }
        }

        Timestamp now = new Timestamp(System.currentTimeMillis());
        Long userId = AuthUtils.getUserId();
        setField(existingEntity, "modifiedDate", now);
        setField(existingEntity, "modifiedUser", userId);

        repository.save(existingEntity);
    }

    public static <T, R extends JpaRepository<T, Long>> void toggleStatus(Long id, R repository) throws Exception {
        if (id == null) {
            throw new IllegalArgumentException("Entity ID cannot be null for toggle status operation");
        }

        Optional<T> existingEntityOpt = repository.findById(id);
        if (existingEntityOpt.isEmpty()) {
            throw new EntityNotFoundException("Entity with ID " + id + " not found");
        }

        T existingEntity = existingEntityOpt.get();

        Field statusField = existingEntity.getClass().getDeclaredField("status");
        statusField.setAccessible(true);
        Object currentStatus = statusField.get(existingEntity);

        Object newStatus = Objects.equals(currentStatus, Constants.STATUS_ACTIVE)
                ? Constants.STATUS_INACTIVE
                : Constants.STATUS_ACTIVE;

        statusField.set(existingEntity, newStatus);

        Timestamp now = new Timestamp(System.currentTimeMillis());
        Long userId = AuthUtils.getUserId();
        setField(existingEntity, "modifiedDate", now);
        setField(existingEntity, "modifiedUser", userId);

        repository.save(existingEntity);
    }

    public static <T, R extends JpaRepository<T, Long>> void deleteEntity(Long id, R repository) throws Exception {
        if (id == null) {
            throw new IllegalArgumentException("Entity ID cannot be null for delete operation");
        }

        Optional<T> existingEntityOpt = repository.findById(id);
        if (existingEntityOpt.isEmpty()) {
            throw new EntityNotFoundException("Entity with ID " + id + " not found");
        }

        T existingEntity = existingEntityOpt.get();

        Timestamp now = new Timestamp(System.currentTimeMillis());
        Long userId = AuthUtils.getUserId();

        setField(existingEntity, "display", Constants.DISPLAY_HIDDEN);
        setField(existingEntity, "modifiedDate", now);
        setField(existingEntity, "modifiedUser", userId);

        repository.save(existingEntity);
    }

    public static <T, R extends JpaRepository<T, Long>> void hardDeleteEntity(Long id, R repository) throws Exception {
        if (id == null) {
            throw new IllegalArgumentException("Entity ID cannot be null for delete operation");
        }

        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Entity with ID " + id + " not found");
        }

        repository.deleteById(id);
    }

    private static Field findIdField(Class<?> clazz) {
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Id.class)) {
                return field;
            }
        }
        try {
            return clazz.getDeclaredField("id");
        } catch (NoSuchFieldException e) {
            return null;
        }
    }
}