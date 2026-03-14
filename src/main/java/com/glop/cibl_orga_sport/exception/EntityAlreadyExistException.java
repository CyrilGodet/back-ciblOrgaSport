package com.glop.cibl_orga_sport.exception;

public class EntityAlreadyExistException extends RuntimeException {
    public EntityAlreadyExistException() {
    }

    public EntityAlreadyExistException(String message) {
        super(message);
    }
}
