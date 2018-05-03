package com.biz.lesson.util;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.persistence.Entity;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.biz.lesson.enums.EnumerableValue;

/**
 * 复制vo的数据到po， 
 * 例：如果po 中有City city 这样一个属性，vo 中有 Long city 或 Long cityId 这样一个属性，则会通过jpa自动查找对应的数据库对应的city，并赋值给该po。
 */
public class BeanUtils {

    private static final Map<Class, Map<String, Field>> classToVoFieldMap = newHashMap();

    private static final Logger logger = LoggerFactory.getLogger(BeanUtils.class);
    
    public static void copyProperties(Object vo, Object po) {

        Assert.notNull(vo);
        Assert.notNull(po);
        Map<String, Field> voClassFields = getClassFieldMap(vo);
        Map<String, Field> poClassFields = getClassFieldMap(po);
        for (Map.Entry<String, Field> fieldEntry : voClassFields.entrySet()) {
            try {
                String voFieldName = fieldEntry.getKey();
                Field voField = fieldEntry.getValue();
                Field poField = poClassFields.get(voFieldName);
                if (poField == null) {
                    continue;
                }
                Object voValue = voField.get(vo);
                Object poFieldValue = null;
                if(!isEntityField(voField) && isEntityField(poField)){
                    Type poFieldGenericType = poField.getGenericType();
                    logger.trace("Process po field:[{}]", poFieldGenericType);
                    if(poFieldGenericType instanceof ParameterizedType) {
                        ParameterizedType parameterizedType = (ParameterizedType)poFieldGenericType;
                        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
                        poFieldValue = findByIds((Class) actualTypeArguments[0], voValue);
                    } else {
                        poFieldValue = findById((Class) poField.getGenericType(), voValue);
                    }
                } else if (Objects.equals(voField.getGenericType(), poField.getGenericType())) {
                    logger.trace("Copy basic data:[{}] to field:{}.", voValue, poField);
                    poFieldValue = voValue;
                } else if(EnumerableValue.class.isAssignableFrom((Class)poField.getGenericType()) && (Objects.equals(voField.getGenericType(), Integer.class) || Objects.equals(voField.getGenericType(), int.class))) {
                    Class enumClass = (Class) poField.getGenericType();
                    EnumerableValue[] enumArrays = (EnumerableValue[]) enumClass.getMethod("values").invoke(enumClass);
                    for (EnumerableValue enumInstance : enumArrays) {
                        if(Objects.equals(enumInstance.getValue(), voValue)){
                            poFieldValue = enumInstance;
                            break;
                        }
                    }
                } else {
                    continue;
                }
                try {
                    poField.set(po, poFieldValue);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static Boolean isEntityField(Field poField){
        Type poFieldGenericType = poField.getGenericType();
        Class entityClass;
        if(poFieldGenericType instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType)poFieldGenericType;
            entityClass = (Class) parameterizedType.getActualTypeArguments()[0];
        } else {
            entityClass = (Class) poFieldGenericType;
        }
        return entityClass.isAnnotationPresent(Entity.class);
    }

    public static Object findById(Class entityClass, Object id) throws Exception {
        if(id == null){
            return null;
        }
        Object repository = findRepository(entityClass);
        if (repository == null) {
            return null;
        } else {
            logger.trace("Find po from {} by '{}'", repository, id);
            Method findOne = repository.getClass().getMethod("findOne", Serializable.class);
            return findOne.invoke(repository, id);
        }
    }

    private static Object findByIds(Class entityClass, Object ids) throws Exception {
        if(ids == null || !((Iterable)ids).iterator().hasNext()){
            return null;
        }
        Object repository = findRepository(entityClass);
        if (repository == null) {
            return null;
        } else {
            logger.trace("Find po from {} by '{}'", repository, ids);
            Method findOne =
                repository.getClass().getMethod("findAll", Iterable.class);
            return findOne.invoke(repository, ids);
        }
    }
    
    private static String firstCharToLowerCase(String str) {
        char firstChar = str.charAt(0);
        if (firstChar >= 'A' && firstChar <= 'Z') {
            char[] arr = str.toCharArray();
            arr[0] += ('a' - 'A');
            return new String(arr);
        }
        return str;
    }
    public static Object findRepository(Class entityClass){
        if (!entityClass.isAnnotationPresent(Entity.class)) {
            return null;
        }
        String repositoryBeanName = firstCharToLowerCase(entityClass.getSimpleName()) + "Repository";
        logger.trace("Find repository:'{}'.", repositoryBeanName);
        return SpringContextUtil.getBean(repositoryBeanName);
    }

    private static Map<String, Field> getClassFieldMap(Object target) {
        Class<?> targetClass = target.getClass();
        Map<String, Field> fieldMap = classToVoFieldMap.get(targetClass);
        if (fieldMap == null) {
            fieldMap = newHashMap();
            List<Field> fields = getInheritedPrivateFields(targetClass);
            if (CollectionUtils.isNotEmpty(fields)) {
                for (Field field : fields) {
                    if (Modifier.isFinal(field.getModifiers()) || Modifier
                        .isStatic(field.getModifiers())) {
                        continue;
                    }
                    field.setAccessible(true);
                    if(!fieldMap.containsKey(field.getName())) {
                        fieldMap.put(field.getName(), field);
                    }
                }
            }
            classToVoFieldMap.put(targetClass, fieldMap);
        }
        return fieldMap;
    }

    private static List<Field> getInheritedPrivateFields(Class<?> type) {

        List<Field> result = newArrayList();
        Class<?> i = type;
        while (i != null && i != Object.class) {
            Collections.addAll(result, i.getDeclaredFields());
            i = i.getSuperclass();
        }
        return result;
    }



}
