package com.biz.lesson.model.config;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.biz.lesson.model.Persistent;

/**
 * 系统配置管理
 */
@Entity
@Table(name="sys_system_property")
public class SystemProperty extends Persistent {

    private static final long serialVersionUID = -8519605923508418092L;

    /**
     * 配置项
     */
    @Column(length = 100)
    private String name;

    /**
     * 配置值
     */
    @Column(columnDefinition = "MEDIUMTEXT")
    private String value;

    /**
     * 是否允许修改
     */
    @Column
    private Boolean editable;
    

    /**
     * 是否允许修改
     */
    @Column(length=255)
    private String description;

    
    
    public SystemProperty() {
        super();
    }

    public SystemProperty(String name, String value, Boolean editable, String description) {
        this();
        this.name = name;
        this.value = value;
        this.editable = editable;
        this.description = description;
    }

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
