package com.sdx.lx.common.utils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


public class RequestUtil {

	/**
	 * 获取客户端ip，使用nginx反向代理时，客户端ip在x-forwarded-for
	 * 
	 * @param request
	 * @return
	 */
	public static String getClientIp() {
		return getClientIp(((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest());
	}

	/**
	 * 获取客户端ip，使用nginx反向代理时，客户端ip在x-forwarded-for
	 * 
	 * @param request
	 * @return
	 */
	public static String getClientIp(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}


}
