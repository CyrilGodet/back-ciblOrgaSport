package com.glop.cibl_orga_sport.exception;

import lombok.Getter;

public class UserNotConnectedException extends RuntimeException {
    @Getter
    private ErrorCodes errorCode;

    public UserNotConnectedException(String message) {
        super(message);
    }

    public UserNotConnectedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotConnectedException(String message, Throwable cause, ErrorCodes errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public UserNotConnectedException(String message, ErrorCodes errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
