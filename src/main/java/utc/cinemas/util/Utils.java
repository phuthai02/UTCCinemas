package utc.cinemas.util;

import utc.cinemas.model.dto.Response;
import utc.cinemas.model.dto.ResponseCode;

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
}