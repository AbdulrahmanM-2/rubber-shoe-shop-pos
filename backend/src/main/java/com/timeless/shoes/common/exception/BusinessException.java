package com.timeless.shoes.common.exception;

/**
 * Custom exception for business logic errors.
 * For example: insufficient stock, invalid operation, credit limit exceeded.
 */
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public BusinessException() {
        super();
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }
}
