package com.courseModel.exception;

public class NotFoundException extends HTTPException {
    public NotFoundException(String message) {
        super(message);
    }

    @Override
    public int getCode() {
        return 404;
    }
}
