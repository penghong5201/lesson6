package com.biz.lesson.web.servlet;

import java.util.Locale;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.biz.lesson.util.SpringContextUtil;

public final class MessageSourceUtil {
    
    private final static Logger logger = LoggerFactory.getLogger(MessageSourceUtil.class);
    
    private static volatile  MessageSource messageSource;

    private static MessageSource getMessageSource() {
        if (messageSource == null) {
            synchronized (MessageSourceUtil.class) {
                if (messageSource == null) {
                    messageSource = SpringContextUtil.getBean(MessageSource.class);
                }
            }
        }
        return messageSource;
    }

    public static String getValidatorI18nMsg(String[] codes, String defaultMsg, Object... argus) {
        try {
            Locale locale = RequestContextUtils.getLocale(DataStorageThreadLocalHolder.getRequest());
            StringBuffer msg = new StringBuffer();
            if (ArrayUtils.isNotEmpty(codes)) {
                if(logger.isDebugEnabled()){
                    logger.debug("codes:{}",codes);
                }
                String i18Msg = "";
                String field = codes[0];
                if(codes.length>=1){
                    if (StringUtils.contains(field, "Vo.")) {
                        field = field.replace("Vo", "");
                    }
                    i18Msg = getI18nMsg(field, argus, codes[1], locale);
                }else{
                    i18Msg = getI18nMsg(field, argus, field, locale);
                }
                if(logger.isDebugEnabled()){
                    logger.debug("field:{},i18Msg:{}",field,i18Msg);
                }
                msg.append(i18Msg);
            }
            msg.append("  ");
            msg.append(defaultMsg);
            return msg.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getI18nMsg(String key, Object[] args, String defaultMessage,Locale locale) {
        try {
            return getMessageSource().getMessage(key, args,defaultMessage, locale);
        } catch (NoSuchMessageException e) {
            return null;
        }
    }
    
    public static String getI18nMsg(String key) {
        try {
            return getMessageSource().getMessage(key, null, RequestContextUtils.getLocale(DataStorageThreadLocalHolder.getRequest()));
        } catch (NoSuchMessageException e) {
            return null;
        }
    }

    public static String getI18nMsg(MessageSourceResolvable objectError) {
        try {
            return getMessageSource().getMessage(objectError, RequestContextUtils.getLocale(DataStorageThreadLocalHolder.getRequest()));
        } catch (NoSuchMessageException e) {
            return null;
        }
    }

    
}
