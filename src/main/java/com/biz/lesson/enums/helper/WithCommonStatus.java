package com.biz.lesson.enums.helper;

import java.util.Collection;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.biz.lesson.enums.CommonStatus;
import com.google.common.collect.Lists;

public interface WithCommonStatus {

	Integer[] getCommonStatus();
	
	public Collection<BaseEnumWithChecked<CommonStatus>> getTripStatusList();

	public void setCommonStatusList(Collection<BaseEnumWithChecked<CommonStatus>> commonStatusList);
	
	default List<Integer> getSelectedTripStatus() {
		List<Integer> initIds = Lists.newArrayList();
		List<Integer> selectedIds = Lists.newArrayList();
		for (BaseEnumWithChecked<CommonStatus> ts : getTripStatusList()) {
			initIds.add(ts.getEnumValue().getValue());
			if (ts.isChecked())
				selectedIds.add(ts.getEnumValue().getValue());
		}
		return CollectionUtils.isEmpty(selectedIds) ? initIds : selectedIds;
	}


}
