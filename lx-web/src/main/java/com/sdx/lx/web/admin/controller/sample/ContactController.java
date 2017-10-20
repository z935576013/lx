package com.sdx.lx.web.admin.controller.sample;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sdx.lx.service.intf.sample.ContactService;
import com.sdx.lx.service.intf.sample.dto.Contact;
import com.sdx.lx.web.util.ControllerUtil;
import com.sdx.lx.web.util.MobilePageParam;

@Controller
@RequestMapping("/contact")
public class ContactController {

	@Autowired
	ContactService contactService;

	@RequestMapping("/contactlist")
	public String needlist(MobilePageParam page, Model model) {
		List<Contact> list = contactService.getContactList();
		model.addAttribute("list", list);
		model.addAttribute("PageParam", ControllerUtil.getPageParam(Long.valueOf(list.size()), page));
		return "/contact/contactlist.ftl";
	}

	@RequestMapping("/edit")
	public String edit(Long id, Integer success, Model model) {
		Contact contact = contactService.getContact(id);
		if (contact == null) {
			contact = new Contact();
		}
		model.addAttribute("contact", contact);
		model.addAttribute("success", success);
		return "/contact/contactedit.ftl";
	}

	@RequestMapping("/save")
	public String save(Contact contact, Model model) {
		Long id = contactService.saveContact(contact);
		return edit(id, 1, model);
	}

	@RequestMapping(value = "delete", produces = "text/html; charset=UTF-8")
	@ResponseBody
	public String delete(Long id) {
		contactService.deleteContact(id);
		return "ok";
	}

}
