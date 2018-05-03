package com.biz.lesson.support.spring;

import java.sql.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;

public class DateConverter implements Converter<String, Date> {
    
    private static final Logger logger = LoggerFactory.getLogger(DateConverter.class);

    @Override
    public Date convert(String source) {
        try {
            if (StringUtils.isNotBlank(source)) {
                if (NumberUtils.isNumber(source)) {
                    return new Date(Long.valueOf(source));
                }
                return Date.valueOf(source);
            }
        } catch (Throwable e) {
            logger.error("converter",e);
        }
        return null;
    }
}