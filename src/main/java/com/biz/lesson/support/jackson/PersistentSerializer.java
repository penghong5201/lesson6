package com.biz.lesson.support.jackson;

import java.io.IOException;
import java.lang.reflect.Method;

import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;

import com.biz.lesson.exception.BusinessException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class PersistentSerializer extends JsonSerializer{  
    @Override  
    public void serialize(Object value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {  
        try{
            Object obj = initializeAndUnproxy(value);
            if(obj!=null){
                Method getId = obj.getClass().getMethod("getId");
                Object result =  getId.invoke(obj);
                jgen.writeString(result.toString());
            }
        }catch(Exception e){
            throw new BusinessException(e);
        }
    }
    
    public static <T> T initializeAndUnproxy(T entity) {
        if (entity == null) {
           return null;
        }

        Hibernate.initialize(entity);
        if (entity instanceof HibernateProxy) {
            entity = (T) ((HibernateProxy) entity).getHibernateLazyInitializer().getImplementation();
        }
        return entity;
    }
}