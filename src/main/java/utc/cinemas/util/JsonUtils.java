package utc.cinemas.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class JsonUtils {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        SimpleModule module = new SimpleModule();
        module.addSerializer(MultipartFile.class, new JsonSerializer<MultipartFile>() {
            @Override
            public void serialize(MultipartFile file, JsonGenerator gen, SerializerProvider serializers) throws IOException {
                gen.writeStartObject();
                gen.writeStringField("name", file.getName());
                gen.writeStringField("originalFilename", file.getOriginalFilename());
                gen.writeStringField("contentType", file.getContentType());
                gen.writeNumberField("size", file.getSize());
                gen.writeEndObject();
            }
        });
        objectMapper.registerModule(module);
    }

    public static String toString(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Lỗi khi chuyển Object sang JSON String", e);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T convert(Object obj, Class<T> targetType) {
        if (obj == null) return null;
        if (targetType.isInstance(obj)) return targetType.cast(obj);

        if (targetType == String.class) return targetType.cast(obj.toString());

        if (obj instanceof String) {
            String str = (String) obj;
            try {
                if (targetType == Integer.class || targetType == int.class) return (T) Integer.valueOf(str);
                if (targetType == Long.class || targetType == long.class) return (T) Long.valueOf(str);
                if (targetType == Double.class || targetType == double.class) return (T) Double.valueOf(str);
                if (targetType == Boolean.class || targetType == boolean.class) return (T) Boolean.valueOf(str);
                if (targetType == Float.class || targetType == float.class) return (T) Float.valueOf(str);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Cannot convert '" + str + "' to " + targetType.getName(), e);
            }
        }

        if (obj instanceof Number) {
            Number num = (Number) obj;
            if (targetType == Integer.class || targetType == int.class) return (T) Integer.valueOf(num.intValue());
            if (targetType == Long.class || targetType == long.class) return (T) Long.valueOf(num.longValue());
            if (targetType == Double.class || targetType == double.class) return (T) Double.valueOf(num.doubleValue());
            if (targetType == Float.class || targetType == float.class) return (T) Float.valueOf(num.floatValue());
        }

        try {
            return objectMapper.convertValue(obj, targetType);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Unsupported conversion from " + obj.getClass().getName() + " to " + targetType.getName(), e);
        }
    }
}