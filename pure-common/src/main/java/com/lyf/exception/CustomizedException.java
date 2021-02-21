package com.lyf.exception;

/**
 * 自定义unchecked异常
 */
public class CustomizedException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public CustomizedException(String message) {
        super(message);
    }

    public CustomizedException(Throwable cause) {
        super(cause);
    }

    public CustomizedException(String message, Throwable cause) {
        super(message, cause);
    }
}
