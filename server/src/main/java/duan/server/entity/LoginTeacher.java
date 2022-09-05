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
public class LoginTeacher implements UserDetails {

    private Teacher teacher;

//存储SpringSecurity所需要的权限信息的集合
    /**
     * 注解防止出现fastjson反序列化失败autoType is not support
     */
    @JSONField(serialize = false)
    private List<GrantedAuthority> authorities;

    public LoginTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //把admin对象中的权限信息封装成SpringSecurity需要的SimpleGrantedAuthority对象

        List<GrantedAuthority> RoleList= new ArrayList<>();
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(teacher.getRole());
        RoleList.add(simpleGrantedAuthority);
        authorities = RoleList;
        return authorities;
    }


    @Override
    public String getPassword() {
        return teacher.getPassword();
    }

    @Override
    public String getUsername() {
        return teacher.getTno();
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
