package com.tomuta.swenoop.http;

public enum HttpStatus {
    OK(200, "OK"),
    CREATED(201, "CREATED"),
    BAD_REQUEST(400, "Bad Request"),
    NOT_FOUND(404, "Not Found"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
    PAYMENT_REQUIRED(402, "Payment Required"),
    FORBIDDEN(403,"Forbidden");

    public final int code;
    public final String message;

    HttpStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
