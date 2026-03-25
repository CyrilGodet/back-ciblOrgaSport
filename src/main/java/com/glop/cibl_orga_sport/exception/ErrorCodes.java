package com.glop.cibl_orga_sport.exception;

public enum ErrorCodes {
    CATEGORY_NOT_FOUND(1000),
    CATEGORY_NOT_VALID(1001),
    CATEGORY_ALREADY_IN_USE(1002),

    USER_NOT_FOUND(3000),
    USER_NOT_VALID(3001),
    USER_ALREADY_IN_USE(3002),
    USER_CHANGE_PASSWORD_OBJECT_NOT_VALID(3003),

    MDP_INVALID(5000),

    ROLES_NOT_VALID(6000),

    ROLES_NOT_FOUND(6000),

    PERMISSION_NOT_FOUND(7000),
    PERMISSION_NOT_VALID(7001),
    PERMISSION_ALREADY_IN_USE(7002),

    CONNECTION_ERROR(8000),

    USER_NOT_CONNECTED(9000);




    private int code;

    ErrorCodes(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
