package com.biz.lesson.enums.converter;

import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.AttributeConverter;

import org.apache.commons.lang3.StringUtils;

import com.biz.lesson.enums.EnumerableValue;
import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

/**
 * 枚举保存list转换
 */
public abstract class BaseListEnumConverter<E extends EnumerableValue> implements AttributeConverter<List<? extends EnumerableValue>,String>{
	
	private static final String separator = ",";

	private Class<E> clz;
	private Map<Integer,E> enumMap = new HashMap<Integer,E>();
	
	@SuppressWarnings("unchecked")
	public BaseListEnumConverter() {
		clz = (Class<E>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		for (E e : (E[]) clz.getEnumConstants()) {
			enumMap.put(Integer.valueOf(e.getValue()), e);
		}
	}
	
	@Override
	public String convertToDatabaseColumn(List<? extends EnumerableValue> list) {
		return this.toString(list);
	}

	@Override
	public List<E> convertToEntityAttribute(String dbData) {
		return this.toList(dbData);
	}

	public String toString(List<? extends EnumerableValue> list) {
		if (list == null) return "";
		List<String> strList = Lists.transform(list, new Function<EnumerableValue,String>(){
			@Override
			public String apply(EnumerableValue input) {
				return String.valueOf(input.getValue());
			}
		});
		return Joiner.on(separator).join(strList);
	}

	public List<E> toList(String dbString){
		if(StringUtils.isEmpty(dbString)||enumMap.isEmpty()) return Collections.emptyList();
		List<E> result = Lists.newArrayList();
		List<String> valueList = Splitter.on(separator).splitToList(dbString);
		for(String value : valueList){
			E e = enumMap.get(Integer.valueOf(value));
			if(e!=null)result.add(e);
		}
		return result;
	};
	
}
