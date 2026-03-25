package com.glop.cibl_orga_sport.exception;

import java.net.ConnectException;
import java.util.List;

public class ConnexionException extends ConnectException {
    private static final long serialVersionUID = 1L;

    private ErrorCodes errorCodes;
    private List<String> errors;

    public ConnexionException(String message) {
        super(message);
    }

    public ConnexionException(String message, ErrorCodes errorCodes, List<String> errors) {
        super(message);
        this.errorCodes = errorCodes;
        this.errors = errors;
    }

    public ErrorCodes getErrorCodes() {
        return errorCodes;
    }

    public List<String> getErrors() {
        return errors;
    }
}
