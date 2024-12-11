package cn.qht2005.cn.dormitoryspringboot.config;

import cn.qht2005.cn.dormitoryspringboot.intercepter.JwtTokenAdminInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebConfig implements WebMvcConfigurer {
	@Autowired
	private JwtTokenAdminInterceptor jwtTokenAdminInterceptor;
	/**
	 *  添加拦截器
	 * @param registry
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 设置不登陆也不会拦截的接口
		registry.addInterceptor(jwtTokenAdminInterceptor).excludePathPatterns("/student/login"
		);
	}
}
