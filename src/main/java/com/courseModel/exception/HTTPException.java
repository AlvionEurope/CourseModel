package com.courseModel.exception;

public abstract class HTTPException extends RuntimeException {
    public HTTPException(String message, Throwable cause) {
        super(message, cause);
    }

    public HTTPException(String message) {
        super(message);
    }

    public abstract int getCode();
}
