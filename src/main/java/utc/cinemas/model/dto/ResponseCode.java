package utc.cinemas.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum ResponseCode {
    SUCCESS(1, "SUCCESS"),
    ERROR(2, "SYSTEM ERROR"),
    WARNING(3, "WARNING"),
    USER_ALREADY_EXISTS(4, "USER ALREADY EXISTS"),
    REQUEST_BODY_INVALID(5, "REQUEST_BODY_INVALID"),
    NOT_FOUND(6, "NOT_FOUND");

    private final int code;
    private final String message;

    ResponseCode(final int code, final String message) {
        this.code = code;
        this.message = message;
    }
}
