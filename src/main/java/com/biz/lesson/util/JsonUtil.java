package com.biz.lesson.util;


import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *Json 序列化反序列化工具类
 */
public class JsonUtil {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
	static {
		OBJECT_MAPPER.configure(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS, true);
		OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	
//		OBJECT_MAPPER.configure(SerializationConfig.FAIL_ON_EMPTY_BEANS,false);
		
		OBJECT_MAPPER.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		OBJECT_MAPPER.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
		
	}


	 public static <T> T json2Obj(byte[] content, Class<T> clazz) {
	        if (content == null) {
	            return null;
	        }

	        try {
	            return OBJECT_MAPPER.readValue(content, clazz);
	        } catch (Exception e) {
	            throw new RuntimeException("Json反序列化出错", e);
	        }
	    }
	

	    public static <T> T json2Obj(String jsonStr, Class<T> clazz) {
	        if (jsonStr == null) {
	            return null;
	        }

	        try {
	            return OBJECT_MAPPER.readValue(jsonStr, clazz);
	        } catch (Exception e) {
	            throw new RuntimeException("Json反序列化出错", e);
	        }
	    }
	 
    public static <T> List<T> json2ObjList(String jsonStr, Class<T> clazz) {
    	
        if (jsonStr == null) {
            return null;
        }
        JavaType javaType = getCollectionType(ArrayList.class, clazz); 
        try {
            return (List<T>)OBJECT_MAPPER.readValue(jsonStr, javaType);
        } catch (Exception e) {
            throw new RuntimeException("Json反序列化出错", e);
        }
    }
    

    public static <T> List<T> jsonByte2ObjList(byte[] jsonByte, Class<T> clazz) {
    	
        if (jsonByte == null || jsonByte.length==0) {
            return null;
        }
        JavaType javaType = getCollectionType(ArrayList.class, clazz); 
        try {
            return (List<T>)OBJECT_MAPPER.readValue(jsonByte, javaType);
        } catch (Exception e) {
            throw new RuntimeException("Json反序列化出错", e);
        }
    }
    
    @SuppressWarnings({"rawtypes", "deprecation"})
    public static <T> T json2Obj(String content, Class<T> clazzItem, Class... classes) {
        if (StringUtils.isBlank(content)) {
            return null;
        }
        JavaType javaType = OBJECT_MAPPER.getTypeFactory().constructParametricType(clazzItem, classes);
        try {
            return OBJECT_MAPPER.readValue(content, javaType);
        } catch (Exception e) {
            throw new RuntimeException("Json反序列化出错", e);
        }
    }


    public static String obj2Json(Object obj) {
        try {
            return OBJECT_MAPPER.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException("Json序列化出错", e);
        }
    }
    

    public static String obj2FormatJson(Object obj) {
        try {
            return OBJECT_MAPPER.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException("Json序列化出错", e);
        }
    }
    
    public static byte[] obj2JsonByte(Object obj) {
        try {
            return OBJECT_MAPPER.writeValueAsBytes(obj);
        } catch (Exception e) {
            throw new RuntimeException("Json序列化出错", e);
        }
    }

    public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {   
        return OBJECT_MAPPER.getTypeFactory().constructParametricType(collectionClass, elementClasses);   
    }
    
    
    public static String formatJson(String jsonStr) {
        if (null == jsonStr || "".equals(jsonStr)) return "";
        StringBuilder sb = new StringBuilder();
        char last = '\0';
        char current = '\0';
        int indent = 0;
        for (int i = 0; i < jsonStr.length(); i++) {
            last = current;
            current = jsonStr.charAt(i);
            switch (current) {
                case '{':
                case '[':
                    sb.append(current);
                    sb.append('\n');
                    indent++;
                    addIndentBlank(sb, indent);
                    break;
                case '}':
                case ']':
                    sb.append('\n');
                    indent--;
                    addIndentBlank(sb, indent);
                    sb.append(current);
                    break;
                case ',':
                    sb.append(current);
                    if (last != '\\') {
                        sb.append('\n');
                        addIndentBlank(sb, indent);
                    }
                    break;
                default:
                    sb.append(current);
            }
        }

        return sb.toString();
    }

    private static void addIndentBlank(StringBuilder sb, int indent) {
        for (int i = 0; i < indent; i++) {
            sb.append('\t');
        }
    }
}
