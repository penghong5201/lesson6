package com.biz.lesson.exception;

public class EnumException  extends RuntimeException {

    private static final long serialVersionUID = 1635227111040864537L;

    public EnumException() {
        super();
    }

    public EnumException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public EnumException(String message, Throwable cause) {
        super(message, cause);
    }

    public EnumException(String message) {
        super(message);
    }

    public EnumException(Throwable cause) {
        super(cause);
    }

}
