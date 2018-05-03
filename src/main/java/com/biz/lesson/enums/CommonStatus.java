package com.biz.lesson.enums;

import com.biz.lesson.enums.converter.BaseEnumValueConverter;
import com.google.common.base.Objects;

public enum CommonStatus implements EnumerableValue {
    
    DELETE      (-10,"Deleted","删除",true),     

    NEW         (10,"New","新建",true),
    CONFIRMED   (40,"Confirmed","确认",true),   
    CANCELED    (-1,"Canceled","取消",true),


    HISTORY     (100,"History","历史",false);
    
    
    private int value;
    private String name;
    private String messageCode;
    private boolean canChange;

    public static class Converter extends BaseEnumValueConverter<CommonStatus> {

    }

    CommonStatus(int value, String name,String messageCode,boolean bool) {
        this.value = value;
        this.name = name;
        this.messageCode = messageCode;
        this.canChange = bool;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public boolean isCanChange() {
		return canChange;
	}

	public static CommonStatus valueOf(Integer type){
        for (CommonStatus s: CommonStatus.values()) {
           if(Objects.equal(type, s.getValue())){
               return s;
           }
        }
        return null;
    }
    
    public static CommonStatus[] valueOf(Integer[] type){
        CommonStatus[] result = new CommonStatus[type.length];
        for(int i=0;i<result.length;i++){
            result[i] = valueOf(type[i]);
        }
        return result;
    }
    
    public boolean isConfirmed(){
    	return getValue() >= CONFIRMED.getValue();
    }
    
    public boolean isFinished(){
    	return this == CommonStatus.HISTORY;
    }


    public boolean isHistory(){
    	return this == HISTORY;
    }

    
    public static CommonStatus[] onOperation() {
        return new CommonStatus[]{NEW,CONFIRMED};
    }
    
    public static CommonStatus[] onHistory() {
        return new CommonStatus[]{HISTORY};
    }
    

}
