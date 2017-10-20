package com.sdx.lx.web.admin.controller.sample;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sdx.lx.service.intf.sample.ContactService;
import com.sdx.lx.service.intf.sample.ItemService;
import com.sdx.lx.service.intf.sample.NeedOrderService;
import com.sdx.lx.service.intf.sample.dto.Contact;
import com.sdx.lx.service.intf.sample.dto.Item;
import com.sdx.lx.service.intf.sample.dto.NeedOrder;
import com.sdx.lx.service.intf.sample.dto.NeedOrderDto;
import com.sdx.lx.web.util.ControllerUtil;
import com.sdx.lx.web.util.MobilePageParam;

@Controller
@RequestMapping("/need")
public class NeedController {

	@Autowired
	NeedOrderService needOrderService;

	@Autowired
	ContactService contactService;

	@Autowired
	ItemService itemService;

	@RequestMapping("/needlist")
	public String needlist(String keyword, MobilePageParam page, Model model) {
		List<NeedOrderDto> list = needOrderService.getNeedOrderListDetail(keyword, page.getStartIndex(),
				page.getMaxCount());
		Long count = needOrderService.getNeedOrderListCount(keyword);
		model.addAttribute("list", list);

		model.addAttribute("keyword", keyword);
		model.addAttribute("PageParam", ControllerUtil.getPageParam(count, page));
		return "/need/needlist.ftl";
	}

	@RequestMapping("/add")
	public String add(Long id, Model model) {
		NeedOrderDto order = needOrderService.getNeedOrderDetail(id);
		List<Contact> contactList = contactService.getContactList();
		List<Item> itemList = itemService.getItemList();
		if (order == null) {
			order = new NeedOrderDto();
		}
		model.addAttribute("order", order);
		model.addAttribute("contactList", contactList);
		model.addAttribute("itemList", itemList);
		return "/need/needadd.ftl";
	}

	@RequestMapping("/save")
	public String save(NeedOrder order, Long[] itemId, String[] productName, String[] spec, Integer[] num,
			String[] itemNote, Model model) {
		needOrderService.saveNeedOrder(order, itemId, productName, spec, num, itemNote);
		return needlist(null, new MobilePageParam(), model);
	}

	@RequestMapping(value = "delete", produces = "text/html; charset=UTF-8")
	@ResponseBody
	public String delete(HttpServletRequest request, Long id) {
		needOrderService.deleteNeedOrder(id);
		return "ok";
	}

	@RequestMapping(value = "deleteItem", produces = "text/html; charset=UTF-8")
	@ResponseBody
	public String deleteItem(HttpServletRequest request, Long id) {
		needOrderService.deleteNeedOrderItem(id);
		return "ok";
	}

	@RequestMapping("/export")
	public void export(HttpServletResponse response, Model model) throws FileNotFoundException, IOException {
		File file = needOrderService.exportNeedOrder();
		if (file != null) {
			response.setHeader("Content-Disposition", "attachment; filename=1.xlsx");
			try (InputStream in = new FileInputStream(file); OutputStream os = response.getOutputStream();) {
				IOUtils.copy(new FileInputStream(file), os);
				os.close();
			}
		}
	}

	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		CustomDateEditor editor = new CustomDateEditor(df, true);
		binder.registerCustomEditor(Date.class, editor);
	}

}
