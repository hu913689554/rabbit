package com.hqm.rabbit.domain.vo;

import com.hqm.rabbit.utils.common.annotation.Excel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @作者 胡勤明
 * @时间 2022-01-05 16:49
 * @版本 1.0
 */
public class SysUser implements UserDetails {
    /**
     * 用户唯一标识
     */
    public String Id;
    /**
     * 用户账号
     */
    @Excel
    public String username;
    /**
     * 用户密码
     */
    @Excel
    public String password;
    /**
     * 登录时间
     */
    private Long loginTime;
    /**
     * 过期时间
     */
    private Long expireTime;
    /**
     * 用户对应token
     */
    private String token;

    /**
     * 用户页面
     */
    private Set<String> pagePath;

    public Set<String> getRagePath() {
        return pagePath;
    }

    public void setRagePath(Set<String> pagePath) {
        this.pagePath = pagePath;
    }

    /**
     * 用户权限
     */
    private Set<String> permissions;

    public Set<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<String> permissions) {
        this.permissions = permissions;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    public void setPassword(String password) { this.password = password; }

    @Override
    public String getPassword() {
        return password;
    }





    public Long getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Long loginTime) {
        this.loginTime = loginTime;
    }

    public Long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Long expireTime) {
        this.expireTime = expireTime;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


//    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
//        this.authorities = authorities;
//    }
    private Collection<? extends GrantedAuthority> authorities;
    /**
     * 返回用户所有角色的封装，一个Role对应一个GrantedAuthority
     *
     * @return 返回授予用户的权限。
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    /**
     * 账户是否未过期
     *
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 账户是否未锁定
     *
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 账户凭证是否未过期
     *
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    /**
     * 创建人 胡勤明
     * 创建时间 2022/1/12 13:34
     * 方法作用 是否停用
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "UserVo{" +
                "Id='" + Id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", loginTime=" + loginTime +
                ", expireTime=" + expireTime +
                ", token='" + token + '\'' +
                ", permissions=" + permissions +
                ", authorities=" + authorities +
                '}';
    }
}