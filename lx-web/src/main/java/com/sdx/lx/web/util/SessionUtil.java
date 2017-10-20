package com.sdx.lx.web.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.sdx.lx.service.intf.sample.dto.UserInfo;

public class SessionUtil {

	public static final String SESSION_USERINFO = "sessionUser";

	public static Map<String, String> SESSION_VALCODE = new HashMap<String, String>();

	/**
	 * 获取session中的userId
	 * 
	 * @param request
	 * @return
	 */
	public static Long getUserId(HttpServletRequest request) {
		return getUserInfo(request).getId();
	}

	/**
	 * 获取session中的账号
	 * 
	 * @param request
	 * @return
	 */
	public static String getLoginId(HttpServletRequest request) {
		return getUserInfo(request).getUserName();
	}

	/**
	 * 获取session中的userId
	 * 
	 * @return
	 */
	public static Long getUserId() {
		return getUserInfo().getId();
	}

	/**
	 * 获取session中的userInfo
	 * 
	 * @return
	 */
	public static UserInfo getUserInfo(HttpServletRequest request) {
		Object userInfo = request.getSession(true).getAttribute(SESSION_USERINFO);
		if (userInfo == null) {
			throw new NeedLoginException();
		} else {
			return (UserInfo) userInfo;
		}
	}

	/**
	 * 获取session中的userInfo
	 * 
	 * @return
	 */
	public static UserInfo getUserInfo() {
		return getUserInfo(((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest());
	}

	public static void setValCode(HttpServletRequest request, String mobile, String code) {
		SESSION_VALCODE.put(mobile, code);
	}

	public static String getValCode(String mobile) {
		if (StringUtils.isNotEmpty(mobile)) {
			return SESSION_VALCODE.get(mobile);
		} else {
			return null;
		}
	}

	/**
	 * 将userInfo设置到session中
	 * 
	 * @param request
	 * @param userId
	 *            用户编号
	 * @return
	 */
	public static void setUserInfo(HttpServletRequest request, UserInfo adminUser) {
		// HttpSession session = request.getSession();
		// if (null != session) {
		// session.invalidate();
		// }
		request.getSession(true).setAttribute(SESSION_USERINFO, adminUser);
	}

	public static void removeSession(HttpSession session) {
		if (session != null) {
			session.removeAttribute(SESSION_USERINFO);
		}
	}

}
