package com.biz.lesson.enums.helper;

import java.util.Collection;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;

import com.biz.lesson.enums.EnumerableValue;
import com.google.common.collect.Maps;

/**
 * 
 * 用来减少 复选框类型的数据的繁琐程度
 */
public final class EnumHelper {
	
	private EnumHelper(){
		
	}


	/**
	 * 根据页面传递的vo 值 和 枚举的默认值 构造勾选的枚举对象list
	 * @param clazz 枚举类名字
	 * @param init 初始值枚举数组
	 * @param selected 选中的枚举value数组
	 * @return
	 */
    public static <T extends EnumerableValue> Collection<BaseEnumWithChecked<T>> getEnumList(Class<T> clazz,T[] init, Integer[] selected) {
        Map<Integer, BaseEnumWithChecked<T>> result = Maps.newLinkedHashMap();
        if (init == null) {
            init = clazz.getEnumConstants();
        }
        for (T s : init) {
            result.put(s.getValue(), new BaseEnumWithChecked<T>(s));
        }
        if (ArrayUtils.isNotEmpty(selected)) {
            for (int id : selected) {
            	BaseEnumWithChecked<T> ts = result.get(id);
                if (ts != null) {
                    ts.setChecked(true);
                }
            }
        }
        return result.values();
    }


}
