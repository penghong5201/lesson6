package com.biz.lesson.enums.converter;

import java.lang.reflect.ParameterizedType;

import javax.persistence.AttributeConverter;

import org.apache.commons.lang.ArrayUtils;

import com.biz.lesson.enums.EnumerableValue;
import com.biz.lesson.exception.EnumException;

/**
 * 枚举基类
 */
public abstract class BaseEnumValueConverter<E extends EnumerableValue> implements AttributeConverter<E, Integer> {

	private Class<E> clz;

	@Override
	public Integer convertToDatabaseColumn(EnumerableValue attribute) {
		return attribute == null ? null : attribute.getValue();
	}

	@Override
	public E convertToEntityAttribute(Integer dbData) {
		return valueOf(dbData);
	}

	@SuppressWarnings("unchecked")
	public BaseEnumValueConverter() {
		try {
			clz = (Class<E>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		} catch (Exception e) {
			throw new EnumException("反射失败", e);
		}
	}

	/**
	 * 根据value获取对应枚举
	 * 
	 * @param value
	 * @return
	 */
	public E valueOf(Integer value) {
		if (value != null && ArrayUtils.isNotEmpty(clz.getEnumConstants())) {
			for (E e : (E[]) clz.getEnumConstants()) {
				if (e.getValue() == value) {
					return e;
				}
			}
		}
		return null;
	}
}
