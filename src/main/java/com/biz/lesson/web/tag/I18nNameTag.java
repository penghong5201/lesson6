package com.biz.lesson.web.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang3.StringEscapeUtils;

import com.biz.lesson.model.base.Name;
import com.biz.lesson.vo.I18nName;
import com.biz.lesson.web.servlet.DataStorageThreadLocalHolder;

import java.io.IOException;

public class I18nNameTag extends TagSupport {

	private static final long serialVersionUID = -2123253186181293149L;

	private Object i18nName = null;
	private boolean escapeXml = false;

	@Override
	public int doStartTag() throws JspException {

		try {
			JspWriter out = pageContext.getOut();
			if (i18nName != null) {
				String text = null;
				if (i18nName instanceof I18nName) {
					I18nName i18nName = (I18nName) this.i18nName;
					text = DataStorageThreadLocalHolder.getI18nName(i18nName);
				} else if (i18nName instanceof Name) {
					Name i18nName = (Name) this.i18nName;
					text = DataStorageThreadLocalHolder.getI18nName(i18nName);
				}
				if (text != null) {
					if(escapeXml){
						out.write(StringEscapeUtils.escapeXml10(text));
					}else{
						out.write(text);
					}
				}
			}
			return SKIP_BODY;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * {@linkplain I18nNameTag#i18nName}
	 */
	public void setI18nName(Object i18nName) {
		this.i18nName = i18nName;
	}

	public void setEscapeXml(boolean escapeXml) {
		this.escapeXml = escapeXml;
	}
	
	
}
