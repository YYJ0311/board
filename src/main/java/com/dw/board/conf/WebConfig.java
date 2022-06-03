package com.dw.board.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.dw.board.interceptor.Interceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer{
	
	@Autowired
	private Interceptor interceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 우리가 만든 interceptor를 스프링에 등록
		registry.addInterceptor(interceptor).excludePathPatterns("/api/v1/logs"); 
		// /api/v1/logs경로는 interceptor에서 제외시킴
	}
}
