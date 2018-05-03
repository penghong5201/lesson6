package com.biz.lesson.model.user;

import java.util.List;

import com.biz.lesson.vo.I18nName;
/**
 * 菜单Vo
 */
public class Menu implements java.io.Serializable, I18nName {


    private static final long serialVersionUID = 4328571310942807223L;
    
    private String name;
    private String nameEn;
    private String url;
    private String icon = "fa fa-list";

    private List<Menu> children;

    public Menu(String name, String nameEn, String url, String icon) {
        this.name = name;
        this.nameEn = nameEn;
        this.url = url;
        this.icon = icon;
    }

    public Menu(String name, String nameEn, String url, String icon, List<Menu> children) {
        this(name, nameEn, url, icon);
        this.children = children;
    }

    public String getName() {
        return name;
    }

    /**
     * {@linkplain Menu#nameEn}
     */
    @Override public String getNameEn() {
        return nameEn;
    }

    /**
     * {@linkplain Menu#nameEn}
     */
    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * {@linkplain Menu#icon}
     */
    public String getIcon() {

        return icon;
    }

    /**
     * {@linkplain Menu#icon}
     */
    public void setIcon(String icon) {

        this.icon = icon;
    }

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }

}
