package com.biz.lesson.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

/**
 * 使用UUID 生成ID的 PO 基类
 */
@SuppressWarnings("serial")
@MappedSuperclass
public class Persistent implements PersistentAble {

    @Id
    @Column(length=40)
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
	protected String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String toString() {
		return getClass().getName() + "-:-" + getId();
	}

	@Override
	public boolean equals(Object o) {

		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Persistent that = (Persistent) o;

		return !(id != null ? !id.equals(that.id) : that.id != null);

	}

	@Override
	public int hashCode() {

		return id != null ? id.hashCode() : 0;
	}
}
