package com.biz.lesson.exception;

import org.springframework.validation.BindingResult;

public class InvalidParameterException extends RuntimeException {

    private static final long serialVersionUID = 2578751715394660434L;
    
    private BindingResult errors;

    public InvalidParameterException(BindingResult bindingResult) {
        super();
        errors = bindingResult;
    }

    public BindingResult getErrors() {
        return errors;
    }

    public void setErrors(BindingResult errors) {
        this.errors = errors;
    }

    
    
}
