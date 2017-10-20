package com.sdx.lx.web.mobile.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.sdx.lx.service.intf.sample.dto.UserInfo;
import com.sdx.lx.web.util.NeedLoginException;
import com.sdx.lx.web.util.SessionUtil;

public class AuthFilter implements Filter {

	public static final List<String> PASS = new ArrayList<String>() {
		private static final long serialVersionUID = 1L;

		{
			add("/admin/*");
			add("/verify/*");
			add("/file/*");
			add("/hotel/floor-image/*");
			add("/hotel/floor-structure/*");
			add("/hotel/meeting/*");
			add("/authCode/*");
			add("/imgCode/*");
			add("/login.do");
			add("/loginPage.do");
			add("/sendValCode.do");
			
		}
	};

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest requestSer, ServletResponse responseSer, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) requestSer;
		HttpServletResponse response = (HttpServletResponse) responseSer;

		// 不需要登录链接直接转发
		if (match(request)) {
			chain.doFilter(request, response);
		} else {
			try {
				UserInfo adminUser = SessionUtil.getUserInfo(request);
				if (adminUser != null) {
					// 已登录直接转发
					chain.doFilter(request, response);
				} else {
					// 未登录跳转到登录页面
					response.sendRedirect("/lx-web/loginPage.do");
				}
			} catch (NeedLoginException e) {
				// 未登录跳转到登录页面
				response.sendRedirect("/lx-web/loginPage.do");
			}
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	private boolean match(HttpServletRequest request) {
		boolean result = false;
		for (String mertUrl : PASS) {
			RequestMatcher urlMatcher = new AntPathRequestMatcher(mertUrl);
			if (urlMatcher.matches(request)) {
				result = true;
				break;
			}
		}
		return result;
	}

}
