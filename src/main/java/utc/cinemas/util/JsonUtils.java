package utc.cinemas.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String toString(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Lỗi khi chuyển Object sang JSON String", e);
        }
    }

    public static <T> T convert(Object obj, Class<T> targetType) {
        if (obj == null) return null;
        if (targetType.isInstance(obj)) return targetType.cast(obj);
        if (targetType == String.class) return targetType.cast(obj.toString());
        if (obj instanceof String) {
            String str = (String) obj;
            if (targetType == Integer.class || targetType == int.class) return targetType.cast(Integer.valueOf(str));
            if (targetType == Long.class || targetType == long.class) return targetType.cast(Long.valueOf(str));
            if (targetType == Double.class || targetType == double.class) return targetType.cast(Double.valueOf(str));
            if (targetType == Boolean.class || targetType == boolean.class) return targetType.cast(Boolean.valueOf(str));
        }
        if (obj instanceof Number) {
            Number num = (Number) obj;
            if (targetType == Integer.class || targetType == int.class) return targetType.cast(num.intValue());
            if (targetType == Long.class || targetType == long.class) return targetType.cast(num.longValue());
            if (targetType == Double.class || targetType == double.class) return targetType.cast(num.doubleValue());
            if (targetType == Float.class || targetType == float.class) return targetType.cast(num.floatValue());
        }
        throw new IllegalArgumentException("Unsupported conversion from " + obj.getClass().getName() + " to " + targetType.getName());
    }
}