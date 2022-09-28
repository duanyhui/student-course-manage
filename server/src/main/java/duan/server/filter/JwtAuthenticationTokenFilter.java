package duan.server.filter;


import duan.server.entity.LoginAdmin;
import duan.server.entity.LoginStudent;
import duan.server.entity.LoginTeacher;
import duan.server.utils.JwtUtil;
import duan.server.utils.RedisCache;
import io.jsonwebtoken.Claims;
import io.lettuce.core.RedisException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * <p>
 *  重写请求过滤器，添加JWT验证
 * </p>
 *
 * @author duanyhui
 * @since 2022-08-30
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver handlerExceptionResolver;

    @Autowired
    private RedisCache redisCache;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //获取请求头中的token
        String token = request.getHeader("token");
        if (!StringUtils.hasText(token)) {
            //没有token，放行
            filterChain.doFilter(request, response);
            return;
        }

        //解析token
        String uid;
        String role;
        try {
            Claims claims = JwtUtil.parseJWT(token);
            uid = claims.getSubject();
            role= claims.get("authorities").toString();
        } catch (Exception e) {
            //TODO 打印日志
//            e.printStackTrace();
            logger.error(e.getMessage());
            //交给全局异常处理之后直接return出去不进行接下来的操作
            handlerExceptionResolver.resolveException(request, response, null, e);
            return;
        }

        //分三种情况处理
        switch (role) {
            case "ROLE_ROOT":
                //管理员
                //从redis中获取详细信息
                LoginAdmin admin = (LoginAdmin)redisCache.getCacheObject("adminuid:"+ uid);
                if(Objects.isNull(admin)){
                    RedisException e = new RedisException("token失效，请重新登录");
                    logger.error(e.getMessage());
                    handlerExceptionResolver.resolveException(request, response, null, e) ;
                    return;
                }

                //把用户信息放入Holders中，以便后续使用
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(admin,null,admin.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                break;
            case "ROLE_STUDENT":
                //学生
                //从redis中获取详细信息
                LoginStudent student = redisCache.getCacheObject("studentuid:"+uid);
                if(Objects.isNull(student)){
                    RedisException e = new RedisException("token失效，请重新登录");
                    logger.error(e.getMessage());
                    handlerExceptionResolver.resolveException(request, response, null, e) ;
                    return;
                }

                //把用户信息放入Holders中，以便后续使用
                UsernamePasswordAuthenticationToken authenticationToken1 =
                        new UsernamePasswordAuthenticationToken(student,null,student.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authenticationToken1);
                break;
            case "ROLE_TEACHER":
                //老师
                //从redis中获取详细信息
                LoginTeacher teacher = redisCache.getCacheObject("teacheruid:"+uid);
                if(Objects.isNull(teacher)){
                    RedisException e = new RedisException("token失效，请重新登录");
                    logger.error(e.getMessage());
                    handlerExceptionResolver.resolveException(request, response, null, e) ;
                    return;
                }
                //把用户信息放入Holders中，以便后续使用
                UsernamePasswordAuthenticationToken authenticationToken2 =                //TODO 要获取权限信息放入authorities中
                        new UsernamePasswordAuthenticationToken(teacher,null,teacher.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authenticationToken2);
                break;

            default:
                //其他情况，抛出异常
                throw new RedisException("token错误");
        }




        //放行
        filterChain.doFilter(request, response);
    }



}
