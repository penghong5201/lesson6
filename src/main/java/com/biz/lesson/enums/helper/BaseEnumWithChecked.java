package com.biz.lesson.enums.helper;

import com.biz.lesson.enums.EnumerableValue;

/**
 * 配合枚举  taglib 和  vo 使用的 枚举对象
 */
public class BaseEnumWithChecked<T extends EnumerableValue> {

	private T enumValue;

	private boolean checked = false;


	public BaseEnumWithChecked() {
		super();
	}

	public BaseEnumWithChecked(T t) {
		this();
		this.enumValue = t;
	}

	public T getEnumValue() {
		return enumValue;
	}

	public void setEnumValue(T enumValue) {
		this.enumValue = enumValue;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

}
