package com.biz.lesson.web.controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Validation;
import javax.validation.Validator;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.biz.lesson.exception.CopyPropertiesException;
import com.biz.lesson.exception.InvalidParameterException;
import com.biz.lesson.exception.NumberIdParameterException;
import com.biz.lesson.exception.PermissionDeniedDataAccessException;
import com.biz.lesson.util.BeanUtils;
import com.biz.lesson.web.servlet.MessageSourceUtil;

public class BaseController {

    protected final Logger logger = LoggerFactory.getLogger(getClass());
    
    private static String SESSION_REFERER = "session_referer";
    
    public static final Validator VALIDATOR = Validation.buildDefaultValidatorFactory().getValidator();
    
    public static final String MULTIPLE_MESSAGE_ATTR_NAME = "multiple_messages";
    public static final String SINGLE_MESSAGE_ATTR_NAME = "single_messages";

    @ExceptionHandler({Exception.class})
    public ModelAndView exception(Exception e) throws Exception {
        ModelAndView result = new ModelAndView("/common/error");
        if(e instanceof NumberIdParameterException){
            return result.addObject(SINGLE_MESSAGE_ATTR_NAME,MessageSourceUtil.getI18nMsg("id.invalid"));
        } else if(e instanceof InvalidParameterException){
            InvalidParameterException invalidParameterException = (InvalidParameterException) e;
            List<ObjectError> objectErrors =  invalidParameterException.getErrors().getAllErrors();
            List<String> error_messages = new ArrayList<>();
            for (ObjectError error : objectErrors) {
                if(logger.isDebugEnabled()){
                logger.debug("objName:{},arg [{}]",error.getObjectName(),error.getArguments());
                    for(Object obj:error.getArguments()){
                        logger.debug("obj:{}",obj);
                        logger.debug("default message:{}",error.getDefaultMessage());
                    }
                }
                String[] codes = null;
                if(error.getArguments()!=null && error.getArguments().length>0){
                    Object arg1 = error.getArguments()[0];
                    if(arg1 instanceof DefaultMessageSourceResolvable){
                        DefaultMessageSourceResolvable messageSourceResolvable = (DefaultMessageSourceResolvable)arg1;
                        codes  = messageSourceResolvable.getCodes();
                    }
                 }
                String i18nMsg = MessageSourceUtil.getValidatorI18nMsg(codes,error.getDefaultMessage(),error.getArguments());
                logger.debug("error msg:{}",i18nMsg);
                error_messages.add(i18nMsg);
            }
            return result.addObject(MULTIPLE_MESSAGE_ATTR_NAME,error_messages);
        }else if(e instanceof PermissionDeniedDataAccessException){
        	return result.addObject(SINGLE_MESSAGE_ATTR_NAME,MessageSourceUtil.getI18nMsg("common.permission.datadeny"));
        }
        
        logger.error("Exception " ,e);
        return result;
    }
    
    protected void error(BindingResult result,HttpServletRequest request) throws InvalidParameterException{
        if(result.hasErrors()){
            throw new InvalidParameterException(result); 
        }
    }

    protected Number validNumberId(String text) throws NumberIdParameterException{
        try{
            return Long.valueOf(text);
        }catch(Throwable t){
            throw new NumberIdParameterException();
        }
    }
    

    protected Number defaultNumberId(String text,Number defaultValue) {
        try{
            return Long.valueOf(text);
        }catch(Throwable t){
           
        }
        return defaultValue;
    }
    
    public void copyProperties(Object vo,Object po){
        try{
            BeanUtils.copyProperties(vo, po);
        }catch(Exception e){
            throw new CopyPropertiesException(e);
        }
    }

    public void addReferer(HttpServletRequest request){
        request.getSession().setAttribute(SESSION_REFERER, request.getHeader("referer"));
    }

    public ModelAndView referer(HttpServletRequest request) throws Exception{
        Object ref = request.getSession().getAttribute(SESSION_REFERER);
        String referer = null;
        if (ref!=null) {
            referer = ref.toString();
            request.getSession().removeAttribute(SESSION_REFERER);
        }else{
            referer = request.getHeader("referer");
        }
      
        if (StringUtils.isNotEmpty(referer)) {
            referer = StringUtils.removeStart(referer, request.getSession().getServletContext().getContextPath());
        } else {
            referer = "/welcome.do";
        }
        return new ModelAndView("redirect:"+referer);
    }

    
}
