package duan.server.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginAdmin implements UserDetails {

    private Admin admin;

//存储SpringSecurity所需要的权限信息的集合
    /**
     * 注解防止出现fastjson反序列化失败autoType is not support
     */
    @JSONField(serialize = false)
    private List<GrantedAuthority> authorities;

    public LoginAdmin(Admin admin) {
        this.admin = admin;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //把admin对象中的权限信息封装成SpringSecurity需要的SimpleGrantedAuthority对象

        List<GrantedAuthority> RoleList= new ArrayList<>();
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(admin.getRole());
        RoleList.add(simpleGrantedAuthority);
        authorities = RoleList;
        return authorities;
    }


    @Override
    public String getPassword() {
        return admin.getPassword();
    }

    @Override
    public String getUsername() {
        return admin.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
