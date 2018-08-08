package com.xgb.org.filter;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.xgb.org.domain.UserTemp;

public class LoginInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		boolean flag = true;
		UserTemp user = (UserTemp)request.getSession().getAttribute("user");
		
		// 请求的路径
		String url = request.getServletPath().toString();
		// 不拦截登录等操作
		if(url.contains("login") || url.contains("loginIn") || url.contains("loginOut")) {
			flag = true;
		}else {
			// 拦截未登录
			if(user == null) {
				response.sendRedirect("/admin/login");
				flag = false;
			}
		}	
		return flag;
				
				
//		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}

	
}
