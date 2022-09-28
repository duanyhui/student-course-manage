package duan.server.handler;



import duan.server.commom.lang.Result;
import io.lettuce.core.RedisException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description: 对Controller层全局异常处理
 * @RestControllerAdvice 捕获异常后，返回json数据类型
 * @Author: duanyhui
 * @Date: 2022/9/2
 */
@RestControllerAdvice
public class ControllerExceptionHandler {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());



	/**
	 * 捕获权限相关失败异常
	 *
	 * @param request 请求
	 * @param e       自定义抛出的异常信息
	 * @return
	 */

	@ExceptionHandler(AccessDeniedException.class)
	public Result accessDeniedExceptionHandler(HttpServletRequest request, AccessDeniedException e) {
		logger.error("Request URL : {}, Exception :", request.getRequestURL(), e);
		return Result.fail(e.getMessage());
	}

	/**
	 * 捕获JWT相关失败异常
	 *
	 * @param request 请求
	 * @param e       自定义抛出的异常信息
	 * @return
	 */
	@ExceptionHandler(AuthenticationServiceException.class)
	public Result authenticationExceptionHandler(HttpServletRequest request, AuthenticationServiceException e) {
		logger.error("Request URL : {}, Exception :", request.getRequestURL(), e);
		return Result.fail( e.getMessage());
	}


	/**
	 * 捕获登录失败异常
	 *
	 * @param request 请求
	 * @param e       自定义抛出的异常信息
	 * @return
	 */
	@ExceptionHandler(UsernameNotFoundException.class)
	public Result usernameNotFoundExceptionHandler(HttpServletRequest request, UsernameNotFoundException e) {
		logger.error("Request URL : {}, Exception :", request.getRequestURL(), e);
		return Result.fail(e.getMessage());
	}

	@ExceptionHandler(RuntimeException.class)
	public Result runtimeExceptionHandler(HttpServletRequest request, RuntimeException e) {
		logger.error("Request URL : {}, Exception :", request.getRequestURL(), e);
		return Result.fail( e.getMessage());
	}

	/**
	 * 捕获Redis中的异常
	 *
	 * @param request 请求
	 * @param e       异常信息
	 * @return
	 */
	@ExceptionHandler(RedisException.class)
	public Result redisExceptionHandler(HttpServletRequest request, RedisException e){
		logger.error("Request URL : {}, Exception :", request.getRequestURL(), e);
		return Result.fail( e.getMessage());
	}



	/**
	 * 捕获其它异常
	 *
	 * @param request 请求
	 * @param e       异常信息
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	public Result exceptionHandler(HttpServletRequest request, Exception e) {
		logger.error("Request URL : {}, Exception :", request.getRequestURL(), e);
		return Result.fail( e.getMessage());
	}
}
