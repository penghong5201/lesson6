package com.biz.lesson.vo.user;

import com.biz.lesson.vo.PersistentVo;

/**
 * Created by zhangjian on 17/4/3.
 */
public class SystemPropertyVo extends PersistentVo {
    private static final long serialVersionUID = -1468879993390529701L;

    private String name;

    private String value;

    private Boolean editable;
    
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Boolean getEditable() {
        return editable;
    }

    public void setEditable(Boolean editable) {
        this.editable = editable;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
}
