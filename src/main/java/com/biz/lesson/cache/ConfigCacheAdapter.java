package com.biz.lesson.cache;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.ObjectName;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.biz.lesson.CacheConstants;
import com.biz.lesson.business.config.ConfigManager;
import com.biz.lesson.event.CacheEventType;
import com.biz.lesson.event.event.ConfigChangedEvent;
import com.biz.lesson.model.config.Config;
import com.biz.lesson.model.config.SystemProperty;
/**
 * 配置缓存适配器
 */
@Component
public class ConfigCacheAdapter extends AbstractCacheAdapter {

    private static final Logger logger = LoggerFactory.getLogger(ConfigCacheAdapter.class);
    
    
    @Autowired
    private ConfigManager configManager;

    private Config config = null;

    @Override
    protected void init() {
        careCacheEventTypes = new CacheEventType[] { CacheEventType.CONFIG };
        loadConfig();
    }

    public Config getConfig() {
        return config;
    }

    private void loadConfig() {
        if (config == null) {
            config = Config.getInstance();
        } else {
            config.clear();
        }

        config.putAll(System.getenv());
        config.putAll(System.getProperties());

        Enumeration<String> names = application.getInitParameterNames();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            String value = application.getInitParameter(name);
            config.put(name, value);
            logger.debug("set property  {} : {}", name, value);
        }

        List<SystemProperty> list = configManager.listProperties();
        for (SystemProperty p : list) {
            config.put(p.getName(), p.getValue());
            if (logger.isDebugEnabled()) {
                logger.debug("set property  {} : {}", p.getName(), p.getValue());
            }
        }

        application.setAttribute(CacheConstants.CONFIG, config);
        logger.info("load config size : {}", config.size());
        

        //从Mbean获得端口
        config.put("http_port", getHttpPort());
        config.put("context_path", application.getContextPath());
        
        logger.debug("mail http prefix:{}",config.getCreateMailHttpPrefix());
        logger.debug("out http prefix:{}",config.getOutUrlPrefix());
        
        
        
        
        application.setAttribute(CacheConstants.CONFIG, config);
        
        super.publishEvent(new ConfigChangedEvent(this));
    }

    protected void refresh(CacheEventType cacheEventType) {
        loadConfig();
    }

    private static String getHttpPort() {
        MBeanServer mBeanServer = null;
        if (MBeanServerFactory.findMBeanServer(null).size() > 0) {
            mBeanServer = (MBeanServer) MBeanServerFactory.findMBeanServer(null).get(0);
        }

        try {
            Set<ObjectName> names = mBeanServer.queryNames(new ObjectName("Catalina:type=Connector,*"), null);
            Iterator<ObjectName> it = names.iterator();

            ObjectName oname = null;
            while (it.hasNext()) {
                oname = (ObjectName) it.next();
                String protocol = mBeanServer.getAttribute(oname, "protocol").toString();
                String scheme = mBeanServer.getAttribute(oname, "scheme").toString();
                String port =  mBeanServer.getAttribute(oname, "port").toString();
                
                logger.debug("mBeanServer protocol :{},scheme :{},port : {}",protocol,scheme,port);
                if (StringUtils.containsIgnoreCase(protocol, "HTTP") && StringUtils.containsIgnoreCase(scheme, "HTTP")) {
                  return port;
                }
            }
        } catch (Exception e) {
            logger.error("getHttpPort",e);
        }
        return "";
    }



}
