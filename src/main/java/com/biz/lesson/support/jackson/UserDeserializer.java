package com.biz.lesson.support.jackson;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;

import com.biz.lesson.business.user.UserManager;
import com.biz.lesson.model.user.User;
import com.biz.lesson.util.SpringContextUtil;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class UserDeserializer extends JsonDeserializer<User>{  
    
    @Override  
    public User deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {  
        String value = jp.getValueAsString();
        if(StringUtils.isNotBlank(value)){
           return SpringContextUtil.getBean(UserManager.class).getUser(value);
        }
        return null;  
    }  
}  
