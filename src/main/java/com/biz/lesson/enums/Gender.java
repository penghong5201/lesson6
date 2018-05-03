package com.biz.lesson.enums;

import com.biz.lesson.enums.converter.BaseEnumValueConverter;

public enum Gender implements EnumerableValue {
    
    
    MALE(1,"M","MR"),
    FEMALE(2,"F","MS");
    
    private int value;
    private String name;
    private String title;

    public static class Converter extends BaseEnumValueConverter<Gender> {

    }

    Gender(int value, String name,String title) {
        this.value = value;
        this.name = name;
        this.title = title;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    
    public String getTitle() {
        return title;
    }
    
}