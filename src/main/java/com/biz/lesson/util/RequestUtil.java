package com.biz.lesson.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class RequestUtil {

	
	public static Pageable Pageable(HttpServletRequest request) {
		int page = 1;
		int pageSize = 2000;
		String s_page = request.getParameter("page");
		String s_pageSize = request.getParameter("pageSize");

		try {
			page = Integer.valueOf(page).intValue();
			if (page <= 0)
				page = 1;
		} catch (Exception e) {
		}

		if (s_pageSize != null) {
			try {
				if (Integer.valueOf(s_pageSize).intValue() > 0)
					pageSize = Integer.valueOf(s_pageSize).intValue();
			} catch (Exception e) {
			}
		}

		return new PageRequest(page, pageSize);
	}
}
