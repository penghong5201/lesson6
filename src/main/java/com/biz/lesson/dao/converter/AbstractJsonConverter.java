package com.biz.lesson.dao.converter;

import javax.persistence.AttributeConverter;

import com.biz.lesson.util.JsonUtil;

/**
 * 有些需要 组合到对象内的子对象,不再建立实体对象,所谓json 存储到 数据库的一个列中
 */
public abstract class AbstractJsonConverter<T> implements AttributeConverter<T, String> {

	public abstract Class<T> getTargetClass();

	@Override
	public String convertToDatabaseColumn(T attribute) {
		return JsonUtil.obj2Json(attribute);
	}

	@Override
	public T convertToEntityAttribute(String dbData) {
		return JsonUtil.json2Obj(dbData, getTargetClass());
	}
}
