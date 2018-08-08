package com.xgb.org.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.xgb.org.filter.LoginInterceptor;

@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer{

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns("/css/**",
				"/js/**","/img/**","/core/**","/fonts/**","/laypage/**","/plugins/**");
		WebMvcConfigurer.super.addInterceptors(registry);
	}

	
}
