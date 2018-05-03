package com.biz.lesson.model;

import java.io.Serializable;

/**
 * PO 基础接口
 */
public interface PersistentAble extends Serializable {

	public Serializable getId();
}
