package com.sdx.lx.web.admin.controller.sample;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sdx.lx.service.intf.sample.UserService;
import com.sdx.lx.service.intf.sample.dto.UserInfo;
import com.sdx.lx.web.util.SessionUtil;

@Controller
@RequestMapping
public class IndexController {

	@Autowired
	UserService userService;

	@RequestMapping("/index")
	public void index(HttpServletResponse response) throws IOException {
		response.sendRedirect("/lx-web/need/needlist.do");
	}

	@RequestMapping("/loginPage")
	public String loginPage(Integer error, String mobile, Model model) {
		model.addAttribute("mobile", mobile);
		model.addAttribute("error", error);
		return "/login2.ftl";
	}

	@RequestMapping(value = "sendValCode", produces = "text/html; charset=UTF-8")
	@ResponseBody
	public String sendValCode(HttpServletRequest request, String mobile) {
		if (StringUtils.isNotEmpty(mobile)) {
			String code = mobile.substring(mobile.length() - 6, mobile.length());
			SessionUtil.setValCode(request, mobile, code);
		}
		return "ok";
	}

	@RequestMapping("/login")
	public String login(HttpServletRequest request, HttpServletResponse response, String mobile, String password,
			Model model) throws IOException {
		UserInfo userInfo = userService.getUser(mobile, password);
		if (userInfo != null) {
			SessionUtil.setUserInfo(request, userInfo);
			response.sendRedirect("/lx-web/index.do");
			return null;
		} else {
			return loginPage(1, mobile, model);
		}

	}

	@RequestMapping("/logout")
	public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		SessionUtil.removeSession(request.getSession());
		response.sendRedirect("/lx-web/index.do");
	}

}
