package com.biz.lesson.model.user;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.biz.lesson.model.PersistentAble;
import com.biz.lesson.vo.I18nName;

/**
 * 主菜单
 */
@Entity
@Table(name = "usr_mainmenu")
public class MainMenu implements PersistentAble, I18nName {

    private static final long serialVersionUID = 850178189967579158L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Integer code;

    @Column(length = 200)
    private String name;

    @Column(length = 200)
    private String nameEn;

    @Column(length = 255)
    private String description;

    @Column(length = 20)
    private String icon = "fa fa-list";

    @Column(length = 200)
    private String companyType;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "mainMenu")
    @OrderBy(value = "code")
    private List<MenuItem> menuItems;

    public MainMenu() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public Integer getCode() {
        return code;
    }

    public Long getId() {
        return id;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String toString() {
        return name;
    }

    /**
     * {@linkplain MainMenu#nameEn}
     */
    public String getNameEn() {
        return nameEn;
    }

    /**
     * {@linkplain MainMenu#nameEn}
     */
    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    /**
     * {@linkplain MainMenu#icon}
     */
    public String getIcon() {

        return icon;
    }

    /**
     * {@linkplain MainMenu#icon}
     */
    public void setIcon(String icon) {

        this.icon = icon;
    }

    /**
     * {@linkplain MainMenu#companyType}
     */
    public String getCompanyType() {
        return companyType;
    }

    /**
     * {@linkplain MainMenu#companyType}
     */
    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }
}
