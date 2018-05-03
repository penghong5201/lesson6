package com.biz.lesson.model.user;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.biz.lesson.model.PersistentAble;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 用户访问记录
 */
@Entity
@Table(name = "usr_accesslog")
public class AccessLogPo implements PersistentAble {


	private static final long serialVersionUID = -1682349799092177474L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	 private Long id;

	 /**
	 * 访问路径
	 */
	@Column(length = 255, nullable = false)
	private String uri;

	/**
	 * 请求方式
	 */
	@Column(length = 10, nullable = false)
	private String http_method;

	/**
	 * 请求参数
	 */
	@JsonIgnore
	@Column(columnDefinition = "MEDIUMTEXT")
	private String requestParams;

	/**
	 * 操作人员
	 */
	@Column(length = 20, nullable = false)
	private String user;

	@Column(nullable = false)
	private Timestamp timestamp;

	public String getUri() {

		return uri;
	}

	public void setUri(String uri) {

		this.uri = uri;
	}

	public String getHttp_method() {

		return http_method;
	}

	public void setHttp_method(String http_method) {

		this.http_method = http_method;
	}

	public String getRequestParams() {

		return requestParams;
	}

	public void setRequestParams(String requestParams) {

		this.requestParams = requestParams;
	}

	public String getUser() {

		return user;
	}

	public void setUser(String user) {

		this.user = user;
	}

	/**
	 * {@linkplain AccessLogPo#timestamp}
	 */
	public Timestamp getTimestamp() {

		return timestamp;
	}

	/**
	 * {@linkplain AccessLogPo#timestamp}
	 */
	public void setTimestamp(Timestamp timestamp) {

		this.timestamp = timestamp;
	}

	@Override
	public Long getId() {

		return id;
	}

	/**
	 * {@linkplain AccessLogPo#id}
	 */
	public void setId(Long id) {

		this.id = id;
	}
}
