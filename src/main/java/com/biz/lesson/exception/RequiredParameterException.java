package com.biz.lesson.exception;

public class RequiredParameterException extends RuntimeException {

    private static final long serialVersionUID = 2578751715394660434L;
    
    private String[] filelds;

    public RequiredParameterException() {
        super();
    }
    
    public RequiredParameterException(String[] filelds) {
        this();
        this.filelds = filelds;
    }

    public String[] getFilelds() {
        return filelds;
    }

    public void setFilelds(String[] filelds) {
        this.filelds = filelds;
    }
}
