package com.biz.lesson.model.user;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.biz.lesson.model.PersistentAble;
import com.biz.lesson.vo.I18nName;

/**
 * 角色
 */
@Entity
@Table(name = "usr_role")
public class Role implements PersistentAble, I18nName {

    private static final long serialVersionUID = 5586977103470006895L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 50, nullable = false)
    private String nameEn;

    @Column(length = 255)
    private String description;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "usr_role_menuitem",
            joinColumns = { @JoinColumn(name = "role_id", referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "menuitem_id", referencedColumnName = "id") },
            uniqueConstraints = { @UniqueConstraint(columnNames = { "role_id", "menuitem_id" }) })
    private List<MenuItem> menuItems;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "usr_role_resource",
            joinColumns = { @JoinColumn(name = "role_id", referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "resource_id", referencedColumnName = "id") },
            uniqueConstraints = { @UniqueConstraint(columnNames = { "role_id", "resource_id" }) })
    private List<Resource> resources;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void addMenuItem(MenuItem menuItem) {
        if (menuItems == null) {
            menuItems = new ArrayList<MenuItem>();
        }
        if (!menuItems.contains(menuItem)) {
            menuItems.add(menuItem);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * {@linkplain Role#nameEn}
     */
    public String getNameEn() {
        return nameEn;
    }

    /**
     * {@linkplain Role#nameEn}
     */
    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public Long getId() {
        return id;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String toString() {
        return getName();
    }

    public List<Resource> getResources() {
        return resources;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }

}
