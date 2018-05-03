package com.biz.lesson.model.user;

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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.biz.lesson.model.PersistentAble;
import com.biz.lesson.vo.I18nName;

/**
 * 资源--功能/按钮
 */
@Entity
@Table(name = "usr_resource")
public class Resource implements PersistentAble, I18nName {

    private static final long serialVersionUID = -4148803079910781570L;
    
    public final static int TYPE_MENU = 0;
    public final static int TYPE_URL = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 50, nullable = false)
    private String nameEn;

    @Column(columnDefinition = "MEDIUMTEXT")
    private String symbol;

    @Column(length = 255)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    private MenuItem menuItem;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "usr_role_resource",
            joinColumns = { @JoinColumn(name = "resource_id", referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "role_id", referencedColumnName = "id") },
            uniqueConstraints = { @UniqueConstraint(columnNames = { "resource_id", "role_id" }) })
    private List<Role> roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * {@linkplain Resource#nameEn}
     */
    public String getNameEn() {
        return nameEn;
    }

    /**
     * {@linkplain Resource#nameEn}
     */
    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void copy(Resource res) {
        this.name = res.name;
        this.nameEn = res.nameEn;
        this.description = res.description;
        this.symbol = res.symbol;
    }
}
