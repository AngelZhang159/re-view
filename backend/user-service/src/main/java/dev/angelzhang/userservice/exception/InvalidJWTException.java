package dev.angelzhang.userservice.exception;

public class InvalidJWTException extends RuntimeException {
    public InvalidJWTException() {
    }

    public InvalidJWTException(String message) {
        super(message);
    }
}
