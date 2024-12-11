package cn.qht2005.cn.dormitoryspringboot.intercepter;

import cn.qht2005.cn.dormitoryspringboot.properties.JwtProperties;
import cn.qht2005.cn.dormitoryspringboot.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 *  管理端未登录校验
 */
@Component
@Slf4j
public class JwtTokenAdminInterceptor implements HandlerInterceptor {
	@Autowired
	private JwtProperties jwtProperties;
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		log.info("开始进行登录校验……");
		try {
			// 令牌校验
			String token = request.getHeader("Authorization").replace("Bearer ", "");
			log.info("获取到token:{}，开始校验……", token);
			Claims claims = JwtUtil.parseJWT(jwtProperties.getSecretKey(), token);
			// 放行
			return true;
		} catch (Exception e) {
			// 校验失败，不放行并且返回401状态码
			log.error("jwt校验失败");
			response.setStatus(401);
			return false;
		}
	}
}
