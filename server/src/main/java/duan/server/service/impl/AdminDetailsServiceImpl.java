package duan.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import duan.server.entity.Admin;
import duan.server.entity.LoginAdmin;
import duan.server.mapper.AdminMapper;
import lombok.SneakyThrows;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AdminDetailsServiceImpl implements UserDetailsService{

    @Autowired
    private AdminMapper adminMapper;


    @SneakyThrows
//TODO 这是个啥注解
    @Override
    public UserDetails loadUserByUsername(String uid) throws UsernameNotFoundException {
        //查询用户信息
        LambdaQueryWrapper<Admin> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Admin::getUid, Integer.parseInt(uid));
        Admin admin = adminMapper.selectOne(wrapper);//查询用户信息，可能为空
        //判断用户是否存在
        if (Objects.isNull(admin)) {
            throw new Exception("用户不存在");
        }

        // TODO: 2020/9/1 将角色权限信息封装到LoginUser中

        //把数据库中的用户信息封装到 UserDetails 对象中
        return new LoginAdmin(admin);
    }
}