package com.courseModel.exception;

public class BadRequestException extends HTTPException{
    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadRequestException(String message) {
        super(message);
    }

    @Override
    public int getCode() {
        return 400;
    }
}
