package com.xgb.org.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
//@EnableWebMvc
//@ComponentScan
public class StaticConfig implements WebMvcConfigurer{

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		//注：ResourceUtils.CLASSPATH_URL_PREFIX就是"classpath:",如果不加这个，就会提示找不到资源
		System.out.println("注：ResourceUtils.CLASSPATH_URL_PREFIX就是\"classpath:\",如果不加这个，就会提示找不到资源");
		registry.addResourceHandler("/admin/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/admin/");
		WebMvcConfigurer.super.addResourceHandlers(registry);
	}
	
}
