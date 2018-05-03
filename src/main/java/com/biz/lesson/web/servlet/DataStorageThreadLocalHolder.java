package com.biz.lesson.web.servlet;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.biz.lesson.exception.UserNotLoginException;
import com.biz.lesson.model.base.Name;
import com.biz.lesson.model.user.User;
import com.biz.lesson.vo.I18nName;

public final class DataStorageThreadLocalHolder {
   
    static ThreadLocal<DataStorage> threadLocalDataStorage = new ThreadLocal<>();
    
    public static void setDataStorage(DataStorage ds){
        threadLocalDataStorage.set(ds);
    }
    
    public static DataStorage getDataStorage(){
        return threadLocalDataStorage.get();
    }
    
    public static HttpServletRequest getRequest() {
        DataStorage ds = threadLocalDataStorage.get();
        if(ds!=null){
            return ds.getRequest();
        }
        return null;
    }

    /**
     * 当前登录用户
     *
     * @throws UserNotLoginException if not login.
     */
    public static User getUser()  {
        DataStorage dataStorage = threadLocalDataStorage.get();
        User user = dataStorage == null ? null : dataStorage.getUser();
        if (user == null) {
            throw new UserNotLoginException();
        }
        return user;
    }

    public static User getPossibleUser() {
        DataStorage dataStorage = threadLocalDataStorage.get();
        return dataStorage == null ? null : dataStorage.getUser();
    }

    /**
     * 获取当前登录用户的UserId
     */
    public static String getUserId() throws UserNotLoginException {
        return getUser().getUserId();
    }
    
    /**
     * 获取当前登录用户的UserId
     */
    public static String getPossibleUserId()  {
    	User user = getPossibleUser();
    	return user==null ? null : user.getUserId();
    }

    /**
     * 当前用户i18n是否为中文(简体，繁体，香港)
     */
    public static Boolean isZhLocale() {

        DataStorage dataStorage = threadLocalDataStorage.get();
        Boolean isZh = dataStorage == null ? null : dataStorage.isZH();
        if(isZh == null){
        	HttpServletRequest request = getRequest();
        	if(request!=null){
	            Locale locale = RequestContextUtils.getLocale(getRequest());
	            isZh = locale.toLanguageTag().toLowerCase().startsWith("zh");
        	}else{
        		isZh = false;
        	}
        }
        if(dataStorage != null) {
            dataStorage.setIsZH(isZh);
        }
        return isZh;
    }

    public static String getI18nName(I18nName i18nName){

        Boolean isZhLocal = isZhLocale();
        String text =  isZhLocal ? i18nName.getName() : i18nName.getNameEn();
        if(StringUtils.isBlank(text)){
            text = isZhLocal ? i18nName.getNameEn() : i18nName.getNameEn();
        }
        return text;
    }

    public static String getI18nName(Name i18nName){

        Boolean isZhLocal = isZhLocale();
        String text = isZhLocal ? i18nName.getPrimary() : i18nName.getSecondary();
        if(StringUtils.isBlank(text)){
            text = isZhLocal ? i18nName.getSecondary() : i18nName.getPrimary();
        }
        return text;
    }
    

    
}
