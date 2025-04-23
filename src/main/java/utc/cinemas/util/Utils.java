package utc.cinemas.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import utc.cinemas.model.dto.Response;
import utc.cinemas.model.dto.ResponseCode;

import java.util.Map;

public class Utils {
    private static final ThreadLocal<Response> responseThreadLocal = ThreadLocal.withInitial(Response::new);

    private static Response getResponse() {
        return responseThreadLocal.get();
    }

    public static Response createResponse(final ResponseCode responseCode) {
        Response response = getResponse();
        response.setCode(responseCode.getCode());
        response.setMessage(responseCode.getMessage());
        response.setData(null);
        return response;
    }

    public static Response createResponse(final ResponseCode responseCode, String message) {
        Response response = createResponse(responseCode);
        response.setMessage(message);
        return response;
    }

    public static Response createResponse(final ResponseCode responseCode, Object data) {
        Response response = createResponse(responseCode);
        response.setData(data);
        return response;
    }

    public static String getSearch(Map<String, String> filters) {
        String search = JsonUtils.convert(filters.get("search"), String.class).trim();
        return "%" + search + "%";
    }

    public static Pageable getPageable(Map<String, String> filters) {
        int page = Integer.parseInt(filters.getOrDefault("page", "0"));
        int pageSize = Integer.parseInt(filters.getOrDefault("pageSize", "20"));
        String sortBy = filters.getOrDefault("sortBy", "id");
        String sortOrder = filters.getOrDefault("sortOrder", "desc");
        Sort.Direction direction = Sort.Direction.fromString(sortOrder);
        return  PageRequest.of(page, pageSize, Sort.by(direction, sortBy));
    }
}