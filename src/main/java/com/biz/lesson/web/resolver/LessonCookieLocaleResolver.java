package com.biz.lesson.web.resolver;

import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import java.util.concurrent.TimeUnit;

public class LessonCookieLocaleResolver extends CookieLocaleResolver {

	@Override
	public Integer getCookieMaxAge() {

		return (int) TimeUnit.HOURS.toSeconds(24 * 365);
	}
}
