package com.biz.lesson.model.user;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.biz.lesson.model.PersistentAble;
import com.biz.lesson.vo.I18nName;

/**
 * 子菜单
 */
@Entity
@Table(name = "usr_menuitem")
public class MenuItem implements PersistentAble, I18nName {

    private static final long serialVersionUID = 4644209989657100958L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = true)
    private Integer code;

    @Column(length = 200, nullable = false)
    private String name;

    @Column(length = 200, nullable = false)
    private String nameEn;

    @Column(length = 20)
    private String icon = "fa fa-list";

    @Column(length = 200)
    private String link;

    @Column(length = 255)
    private String description;

    @Column(columnDefinition = "MEDIUMTEXT")
    private String symbol;

    @ManyToOne(fetch = FetchType.LAZY)
    private MainMenu mainMenu;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "menuItem")
    private List<Resource> resources;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "usr_role_menuitem",
            joinColumns = { @JoinColumn(name = "menuitem_id", referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "role_id", referencedColumnName = "id") },
            uniqueConstraints = { @UniqueConstraint(columnNames = { "menuitem_id", "role_id" }) })
    private List<Role> roles;

    public MenuItem() {

    }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public String getLink() {
        return link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * {@linkplain MenuItem#nameEn}
     */
    public String getNameEn() {
        return nameEn;
    }

    /**
     * {@linkplain MenuItem#nameEn}
     */
    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    /**
     * {@linkplain MenuItem#icon}
     */
    public String getIcon() {

        return icon;
    }

    /**
     * {@linkplain MenuItem#icon}
     */
    public void setIcon(String icon) {

        this.icon = icon;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public MainMenu getMainMenu() {
        return mainMenu;
    }

    public Long getId() {
        return id;
    }

    public void setMainMenu(MainMenu mainMenu) {
        this.mainMenu = mainMenu;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String toString() {
        return name;
    }

    public List<Resource> getResources() {
        return resources;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }

    public String getSymbol() {
        if (symbol != null) {
            return symbol.replaceAll(";", ",");
        } else
            return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void copy(MenuItem item) {
        this.code = item.code;
        this.name = item.name;
        this.nameEn = item.nameEn;
        this.link = item.link;
        this.description = item.description;
        this.symbol = item.symbol;
        this.icon = item.icon;
    }

}
