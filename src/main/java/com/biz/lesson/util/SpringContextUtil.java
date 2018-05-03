/**
 *
 */
package com.biz.lesson.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

/**
 * Spring容器工具
 * <p/>
 * <p>该工具类需要先注册成为spring容器中的bean</p>
 */
@Service public class SpringContextUtil implements ApplicationContextAware {

    private static ApplicationContext APPLICATIONCONTEXT;


    @Override public void setApplicationContext(ApplicationContext applicationContext)
        throws BeansException {
        APPLICATIONCONTEXT = applicationContext;
    }

    /**
     * 获取Spring容器
     *
     * @return
     */
    public static ApplicationContext getContext() {
        return APPLICATIONCONTEXT;
    }


    public static <T> T getBean(Class<T> beanType) {
        return APPLICATIONCONTEXT == null ? null : APPLICATIONCONTEXT.getBean(beanType);
    }

    public static Object getBean(String beanName) {
        return APPLICATIONCONTEXT == null ? null : APPLICATIONCONTEXT.getBean(beanName);
    }

}
