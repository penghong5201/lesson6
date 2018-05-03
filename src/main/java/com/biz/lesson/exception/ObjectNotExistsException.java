package com.biz.lesson.exception;

public class ObjectNotExistsException extends RuntimeException {


    private static final long serialVersionUID = -587102057901804854L;

    private Object id;
    
    public ObjectNotExistsException(Object id) {
        super();
        this.id = id;
    }

    public Object getId() {
        return id;
    }

    
    

}
