package com.biz.lesson.model.config;

import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.biz.lesson.util.JsonUtil;

/**
 * 系统配置
 */
public final class Config {

    private static final Logger logger = LoggerFactory.getLogger(Config.class);

    private Properties properties = new Properties();

    private static volatile Config instance;

    private Config() {
    }

    public static Config getInstance() {
        if (instance == null) {
            synchronized (Config.class) {
                if (instance == null) {
                    instance = new Config();
                }
            }
        }
        return instance;
    }

    public void clear() {
        properties.clear();
    }

    public void putAll(Map<String, String> t) {
        properties.putAll(t);
    }

    public void putAll(Properties p) {
        properties.putAll(p);
    }

    public void put(String key, String value) {
        properties.put(key, value);
    }

    public int size() {
        return properties.size();
    }


    public Properties getProperties() {
        return properties;
    }

    public Integer getInt(String key, int defaultValue) {
        Integer result = defaultValue;
        try {
            result = Integer.valueOf(get(key));
        } catch (Exception e) {
        }
        return result;
    }

    public String getHostNamePostfix() {
        return StringUtils.defaultIfEmpty(properties.getProperty("host_name_postfix"), ".cn");
    }

    public String get(String key) {
        String value = properties.getProperty(key + getHostNamePostfix());
        if (StringUtils.isBlank(value)) {
            value = properties.getProperty(key);
        }
        return value;
    }


    public String get(String key, String defaultValue) {
        String value = properties.getProperty(key + getHostNamePostfix());
        if (StringUtils.isBlank(value)) {
            value = properties.getProperty(key, defaultValue);
        }
        return value;
    }

    /**
     * google 地图使用的域名
     */
    public String getGoogleApiHostName() {
        return get("google_api_domain_name", "maps.google.cn");
    }

    /**
     * googlemap API Key
     */
    public String getGoogleApiKey() {
        return get("google_map_key", "屏蔽");
    }

    /**
     * 邮件server
     */
    public String getMailServer() {
        return properties.getProperty("default_mail_server");
    }

    /**
     * 默认发件人密码
     */
    public String getMailPassword() {
        return properties.getProperty("default_mail_password");
    }

    public String getMailAddress() {
        return properties.getProperty("default_mail_address");
    }

    /**
     * 是否启动发送邮件线程
     */
    public boolean getSendMail() {
        return StringUtils.equalsIgnoreCase(get("send_mail", "true"), "true");
    }

    /**
     * 是否允许一个账号多个浏览器同时登陆系统
     */
    public boolean getAllowMultipleLogin() {
        return StringUtils.equalsIgnoreCase(get("allow_multiple_login"), "true");
    }



    /**
     * tomcat启动端口
     */
    public String getHttpPort() {
        if (isProxy()) {
            return "";
        } else {
            return ":" + properties.getProperty("http_port");
        }
    }

    /**
     * context_path
     */
    public String getContextPath() {
        return properties.getProperty("context_path");
    }

    /**
     * 创建邮件使用的http前缀
     */
    public String getCreateMailHttpPrefix() {
        return "http://127.0.0.1" + getHttpPort() + getContextPath();
    }

    /**
     * 系统域名
     */
    public String getDomain() {
        return get("domain");
    }

    public String getEmailRefererHost() {
        return "http://" + get("domain.net") + getHttpPort() + getContextPath();
    }

    public String getDocRefererHost() {
        return "http://" + get("domain.cn") + getHttpPort() + getContextPath();
    }


    /**
     * 邮件中给出的对外连接的http前缀
     */
    public String getOutUrlPrefix() {
        return "http://" + getDomain() + getHttpPort() + getContextPath();
    }


    public Boolean getAccessLogSaveSync() {
        return StringUtils.equalsIgnoreCase(get("access_log_save_sync", "true"), "true");
    }


    /**
     * 七牛空间名称
     */
    public String getQiNiuBucket() {
        return get("qiniu.bucket");
    }

    /**
     * 七牛Access Key
     */
    public String getQiNiuAK() {
        return get("qiniu.ak");
    }

    /**
     * 七牛Secret Key
     */
    public String getQiNiuSK() {
        return get("qiniu.sk");
    }

    /**
     * 七牛空间访问域名
     */
    public String getQiNiuDomain() {
        return get("qiniu.domain");
    }


    public String getHttpProtocol() {
        return get("http_protocol", "http");
    }

    public String[] getSMTPServers() {
        return StringUtils.split(get("send_smtp_servers"), ",");
    }

    public boolean isProxy() {
        return StringUtils.equalsIgnoreCase(get("nginx_proxy"), "true");
    }

    public String getCodeServiceUrl() {
        return get("code_service_url");
    }

    public String getSystemLogo() {
        return get("system_logo", "login_1.jpg");
    }

	
	public <T> T getJsonObject(String key,Class<T> t){
		String value = this.get(key);
		return JsonUtil.json2Obj(value, t);
	}

}

