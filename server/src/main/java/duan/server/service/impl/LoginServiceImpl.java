package duan.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import duan.server.commom.lang.Result;
import duan.server.entity.*;
import duan.server.mapper.StudentMapper;
import duan.server.mapper.TeacherMapper;
import duan.server.utils.HashUtils;
import duan.server.utils.JwtUtil;
import duan.server.utils.RedisCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
@Slf4j
public class LoginServiceImpl {
    @Autowired
    private RedisCache redisCache;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AdminServiceImpl adminService;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private TeacherMapper teacherMapper;

    /**
     * 用户登录并返回token
     * @param
     * @return token
     */

    public Result login(Admin admin) throws Exception {


        //TODO 解决"Handler dispatch failed; nested exception is java.lang.StackOverflowError"
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(admin.getUid(),admin.getPassword());
        Authentication authenticate = authenticationManager.authenticate(token);
        //认证未通过抛出异常
        if(Objects.isNull(authenticate)){
            throw new Exception("用户名或密码错误");
        }
        //认证通过，用uid生成一个jwt存入Result中返回给前端

        LoginAdmin loginAdmin= (LoginAdmin) authenticate.getPrincipal();
        String uid = loginAdmin.getAdmin().getUid().toString();
        Collection<? extends GrantedAuthority> authorities = loginAdmin.getAuthorities();
        String jwt = JwtUtil.createJWT(uid, authorities);

        Map<String,String> map=new HashMap<>();
        map.put("token",jwt);
        //把完整的用户信息存入Redis中，key为uid，value为完整的用户信息
        redisCache.setCacheObject("adminuid:"+uid,loginAdmin);
        log.info("用户uid:"+loginAdmin.getAdmin().getUid()+"登录成功,权限为"+loginAdmin.getAuthorities());
        return Result.succ(map);
    }

    public Result login(Student student) throws Exception {
        LambdaQueryWrapper<Student> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Student::getSno, student.getSno());
        Student stuIndb = studentMapper.selectOne(wrapper);//查询用户信息，可能为空
        //判断用户是否存在
        if (Objects.isNull(stuIndb)) {
            throw new Exception("用户不存在");
        }
        if(!HashUtils.matchBC(student.getPassword(),stuIndb.getPassword())){
            throw new Exception("密码错误");
        }
        //认证通过，用uid生成一个jwt存入Result中返回给前端

        LoginStudent loginStudent= new LoginStudent(stuIndb);
        String uid = loginStudent.getStudent().getSno().toString();
        Collection<? extends GrantedAuthority> authorities = loginStudent.getAuthorities();
        String jwt = JwtUtil.createJWT(uid, authorities);

        Map<String,String> map=new HashMap<>();
        map.put("token",jwt);
        //把完整的用户信息存入Redis中，key为uid，value为完整的用户信息
        redisCache.setCacheObject("studentuid:"+uid,loginStudent);
        log.info("学生uid:"+loginStudent.getStudent().getSno()+"登录成功,权限为"+loginStudent.getAuthorities());
        return Result.succ(map);
    }

    public Result login(Teacher teacher) throws Exception {
        LambdaQueryWrapper<Teacher> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Teacher::getTno, teacher.getTno());
        Teacher teaIndb = teacherMapper.selectOne(wrapper);//查询用户信息，可能为空
        if (Objects.isNull(teaIndb)) {
            throw new Exception("用户不存在");
        }
        if(!HashUtils.matchBC(teacher.getPassword(),teaIndb.getPassword())){
            throw new Exception("密码错误");
        }
        //认证通过，用uid生成一个jwt存入Result中返回给前端

        LoginTeacher loginTeacher= new LoginTeacher(teaIndb);
        String uid = loginTeacher.getTeacher().getTno().toString();
        Collection<? extends GrantedAuthority> authorities = loginTeacher.getAuthorities();
        String jwt = JwtUtil.createJWT(uid, authorities);

        Map<String,String> map=new HashMap<>();
        map.put("token",jwt);
        //把完整的用户信息存入Redis中，key为uid，value为完整的用户信息
        redisCache.setCacheObject("teacheruid:"+uid,loginTeacher);
        log.info("老师uid:"+loginTeacher.getTeacher().getTno()+"登录成功,权限为"+loginTeacher.getAuthorities());
        return Result.succ(map);
    }


    public Result logout() {
        //获取当前用户
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //获取当前用户的uid
        String uid = authentication.getName();
        //删除Redis中的用户信息
        switch (authentication.getAuthorities().toString()){
            case "[ROLE_ADMIN]":
                redisCache.deleteObject("adminuid:"+uid);
                log.info("管理员uid:"+uid+"退出登录");
                break;
            case "[ROLE_STUDENT]":
                redisCache.deleteObject("studentuid:"+uid);
                log.info("学生uid:"+uid+"退出登录");
                break;
            case "[ROLE_TEACHER]":
                redisCache.deleteObject("teacheruid:"+uid);
                log.info("老师uid:"+uid+"退出登录");
                break;
        }

        return Result.succ(null);

    }



}
