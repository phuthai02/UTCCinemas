package utc.cinemas.util;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class DatabaseUtils {

    public static <T> Map<String, Object> getList(
            Map<String, String> filters,
            Function<Pageable, Page<T>> fetchFunction
    ) {
        int page = Integer.parseInt(filters.getOrDefault("page", "0"));
        int pageSize = Integer.parseInt(filters.getOrDefault("pageSize", "20"));
        String sortBy = filters.getOrDefault("sortBy", "id");
        String sortOrder = filters.getOrDefault("sortOrder", "desc");

        Sort.Direction direction = Sort.Direction.fromString(sortOrder);
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by(direction, sortBy));

        Page<T> resultPage = fetchFunction.apply(pageable);

        Map<String, Object> response = new HashMap<>();
        response.put("data", resultPage.getContent());
        response.put("totalPages", resultPage.getTotalPages());

        return response;
    }

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
