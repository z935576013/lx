package com.sdx.lx.web.admin.controller.sample;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sdx.lx.service.intf.sample.UserService;
import com.sdx.lx.service.intf.sample.dto.UserInfo;
import com.sdx.lx.web.util.ControllerUtil;
import com.sdx.lx.web.util.MobilePageParam;

@Controller
@RequestMapping("/user")
public class UserInfoController {

	@Autowired
	UserService userService;

	@RequestMapping("/userlist")
	public String needlist(MobilePageParam page, Model model) {
		List<UserInfo> list = userService.getUserList();
		model.addAttribute("list", list);
		model.addAttribute("PageParam", ControllerUtil.getPageParam(Long.valueOf(list.size()), page));
		return "/user/userlist.ftl";
	}

	@RequestMapping("/edit")
	public String edit(Long id, Integer success, Model model) {
		UserInfo user = userService.get(id);
		if (user == null) {
			user = new UserInfo();
			user.setUserType(1);
		}
		model.addAttribute("user", user);
		model.addAttribute("success", success);
		return "/user/useredit.ftl";
	}

	@RequestMapping("/save")
	public String save(UserInfo user, Model model) {
		Long id = userService.saveUser(user);
		return edit(id, 1, model);
	}

	@RequestMapping(value = "delete", produces = "text/html; charset=UTF-8")
	@ResponseBody
	public String delete(Long id) {
		userService.deleteUser(id);
		return "ok";
	}

	@RequestMapping(value = "checkName", produces = "text/html; charset=UTF-8")
	@ResponseBody
	public String checkName(String userName) {
		UserInfo user = userService.getUserByName(userName);
		if (user == null) {
			return "ok";
		} else {
			return "exist";
		}
	}

	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		CustomDateEditor editor = new CustomDateEditor(df, true);
		binder.registerCustomEditor(Date.class, editor);
	}

}
