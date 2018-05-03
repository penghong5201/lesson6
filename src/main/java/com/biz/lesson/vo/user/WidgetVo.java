package com.biz.lesson.vo.user;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.SafeHtml;

import com.biz.lesson.vo.PersistentVo;

public class WidgetVo extends PersistentVo {

	private static final long serialVersionUID = 2571728003170750956L;

	@NotNull
    private String name;

    @NotNull
    private Integer type;

    @SafeHtml
    private String description;

    @SafeHtml
    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
