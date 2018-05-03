package com.biz.lesson.model.user;

import java.sql.Date;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.biz.lesson.model.PersistentAble;
import com.biz.lesson.vo.I18nName;
import com.biz.lesson.web.servlet.DataStorageThreadLocalHolder;
import com.google.common.collect.Maps;

/**
 * 用户
 */
@Entity
@Table(name = "usr_user")
public class User implements PersistentAble, UserDetails, I18nName {


    private static final long serialVersionUID = 2269487776204934381L;
    
    
    public static final String UT_ADMIN = "admin";
    public static final String UT_AGENT = "agent";

    
    @Id
    @Column(length = 20, nullable = false)
    private String userId;

    @Column(length = 200, nullable = false)
    private String password;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 50)
    private String nameEn;
    
    @Column(length = 1)
    private String gender;


    @Column(length = 50)
    private String tel;

    @Column(length = 50)
    private String fax;

    @Column(length = 50)
    private String mobile;

    @Column(length = 20)
    private String userType = UT_ADMIN;

    @Column(length = 255)
    private String address;

    @Column(length = 20)
    private String postcode;


    @Column(length = 100)
    private String permitIP;

    @Column(length = 100)
    private String createBy;


    @Column(length = 50)
    private String email;
    
    @Column(length = 100)
    private String emailPassword;
    
    @Column(length = 100)
    private String smtpServer;

    @Column
    private Date createDate;

    @Column(length = 255)
    protected String logo;

    @Column(length = 255)
    protected String serviceType;

    @Column
    private Boolean status = Boolean.TRUE;

    @Column
    private Boolean navbar = Boolean.FALSE;

    @Column
    private Boolean sidebar = Boolean.FALSE;

    @Column
    private String skin ;

    
    /**
     * 所拥有的权限
     */
    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinTable(name = "usr_user_role", joinColumns = {
            @JoinColumn(name = "userId", referencedColumnName = "userId")}, inverseJoinColumns = {
            @JoinColumn(name = "roleId", referencedColumnName = "id")}, uniqueConstraints = {
            @UniqueConstraint(columnNames = {"userId", "roleId"})})
    private List<Role> roles;

    @Transient
    private List<Menu> menus;
    
    @Transient
    private Set<GrantedAuthority> auths = new HashSet<GrantedAuthority>();


    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public boolean getServiceTypeMap(String serviceType){
        if (StringUtils.isBlank(this.serviceType)){
            return true;
        }
        if ( this.serviceType.contains(serviceType)){
            return true;
        }else {
            return false;
        }
    }

    public boolean getServiceTypeMap(int serviceType){
        if (StringUtils.isBlank(this.serviceType)){
            return true;
        }
        if ( this.serviceType.contains(String.valueOf(serviceType))){
            return true;
        }else {
            return false;
        }
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameEn() {
        return this.nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public Boolean getStatus() {
        return status;
    }

    public String getUserType() {
        return userType;
    }

    public String getFax() {
        return fax;
    }

    public String getMobile() {
        return mobile;
    }

    public String getAddress() {
        return address;
    }

    public String getPermitIP() {
        return permitIP;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPermitIP(String permitIP) {
        this.permitIP = permitIP;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getCreateBy() {
        return createBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public String getPostcode() {
        return postcode;
    }
    
    public String getEmailPassword() {
        return emailPassword;
    }

    public void setEmailPassword(String emailPassword) {
        this.emailPassword = emailPassword;
    }
    
    /**
     * 是否是系统管理员 （非客户用户）
     *
     * @return boolean
     */
    public boolean getIsAdmin() {
        return StringUtils.equalsIgnoreCase(userType, User.UT_ADMIN);
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(this.getUserId());
        if (getName() != null && getName().trim().length() > 0) {
            sb.append("(");
            sb.append(this.getName());
            sb.append(")");
        }
        return sb.toString();
    }

    /**
     * {@linkplain User#menus}
     */
    public List<Menu> getMenus() {
        return menus;
    }

    /**
     * {@linkplain User#menus}
     */
    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    public String getUsername() {
        return userId;
    }

    public String getId() {
       return this.getUserId();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSmtpServer() {
        return smtpServer;
    }

    public void setSmtpServer(String smtpServer) {
        this.smtpServer = smtpServer;
    }
    
    public boolean isMailSettingOk(){
    	return StringUtils.isNotBlank(smtpServer) && StringUtils.isNotBlank(email) && StringUtils.isNotBlank(emailPassword);
    }

    public  Map<Long ,Boolean> getRoleMap(){
        Map<Long ,Boolean> map = Maps.newHashMap();
        if (CollectionUtils.isNotEmpty(roles)){
        	roles.forEach(o->{map.put(o.getId(), true);});
        }
        return map;
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return Objects.equals(status,true);
    }

    public Collection<GrantedAuthority> getAuthorities() {
        auths.clear();
        if (roles != null) {
            for (Role role : roles) {
                List<MenuItem> menuItems = role.getMenuItems();
                for (MenuItem menuItem : menuItems) {
                    if (org.apache.commons.lang3.StringUtils.isNotBlank(menuItem.getSymbol())) {
                        String[] roleSymbol = menuItem.getSymbol().split("[^\\w_]+");
                        for (String sybmol : roleSymbol) {
                            auths.add(new UserAuthority(sybmol));
                        }
                    }
                }
                List<Resource> resources = role.getResources();
                for (Resource resource : resources) {
                    if (org.apache.commons.lang3.StringUtils.isNotBlank(resource.getSymbol())) {
                        String[] roleSymbol = resource.getSymbol().split("[^\\w_]+");
                        for (String sybmol : roleSymbol) {
                            auths.add(new UserAuthority(sybmol));
                        }
                    }
                }
            }
        }
        return auths;
    }


    public String getNameI18n(){
        return DataStorageThreadLocalHolder.isZhLocale()?name:nameEn;
    }

    public Boolean getNavbar() {
        return navbar;
    }

    public void setNavbar(Boolean navbar) {
        this.navbar = navbar;
    }

    public Boolean getSidebar() {
        return sidebar;
    }

    public void setSidebar(Boolean sidebar) {
        this.sidebar = sidebar;
    }

    public String getSkin() {
        return skin;
    }

    public void setSkin(String skin) {
        this.skin = skin;
    }
    

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        User user = (User) o;

        return (Objects.equals(userId, user.userId) && userId != null);
    }

    @Override
    public int hashCode() {
        if (userId == null) {
        	return 0;
        } else {
            return userId.hashCode();
        }
    }
    
    public boolean hasAuthority(String auth){
    	return this.auths.contains(new UserAuthority(auth));
    }
}
