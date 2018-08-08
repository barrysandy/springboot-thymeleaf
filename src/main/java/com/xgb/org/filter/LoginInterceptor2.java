package com.xgb.org.filter;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.xgb.org.domain.UserTemp;

public class LoginInterceptor2 implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 请求的路径
		String url = request.getServletPath().toString();
		//请求url地址需要currentUser(管理后台的session为currentUser)
		HttpSession session = request.getSession();
		UserTemp user = ((UserTemp) session.getAttribute("user"));
		
		
		System.out.println("-----------------------------");
		System.out.println("-----------------------------url: " + url);
		System.out.println("-----------------------------user: " + user);
		
		
		if (user == null) {
			if (url.contains("login") || url.contains("loginIn") || url.contains("loginOut")) {
				return true;
			}else{
				request.getRequestDispatcher("/login").forward(request, response);
				return true;
				
			}
			
		}
		return true;
				
				
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
