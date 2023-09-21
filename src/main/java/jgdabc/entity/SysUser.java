package jgdabc.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.Transient;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * sys_user
 */
@NoArgsConstructor
@Data
public class SysUser implements Serializable, UserDetails {
    /**
     * 编号
     */
    private Integer id;

    /**
     * 登陆名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 性别
     */
    private String sex;

    /**
     * 地址
     */
    private String address;

    /**
     * 是否启动账户0禁用 1启用
     */

    private Integer enabled;

    /**
     * 账户是否没有过期0已过期 1 正常
     */
    private Integer accountNoExpired;

    /**
     * 密码是否没有过期0已过期 1 正常
     */
    private Integer credentialsNoExpired;

    /**
     * 账户是否没有锁定0已锁定 1 正常
     */
    private Integer accountNoLocked;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;


    private List <String> authoritie;

    public void setAuthorities(List<String> authorities) {
        this.authoritie = authorities;
    }



    @Override
    @JsonIgnore
    public Collection<?extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> list = new ArrayList<>();
        this.getAuthoritie().forEach( auth -> {
            list.add(new SimpleGrantedAuthority(auth));
        });
        return  list;

    }

    @Override
    @JsonIgnore

    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }


}