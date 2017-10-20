package com.sdx.lx.web.admin.controller.sample;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sdx.lx.service.intf.sample.ItemService;
import com.sdx.lx.service.intf.sample.dto.Item;
import com.sdx.lx.web.util.ControllerUtil;
import com.sdx.lx.web.util.MobilePageParam;

@Controller
@RequestMapping("/item")
public class ItemController {

	@Autowired
	ItemService itemService;

	@RequestMapping("/itemlist")
	public String needlist(MobilePageParam page, Model model) {
		List<Item> list = itemService.getItemList();
		model.addAttribute("list", list);
		model.addAttribute("PageParam", ControllerUtil.getPageParam(Long.valueOf(list.size()), page));
		return "/item/itemlist.ftl";
	}

	@RequestMapping("/edit")
	public String edit(Long id, Integer success, Model model) {
		Item item = itemService.getItem(id);
		if (item == null) {
			item = new Item();
		}
		model.addAttribute("item", item);
		model.addAttribute("success", success);
		return "/item/itemedit.ftl";
	}

	@RequestMapping("/save")
	public String save(Item item, Model model) {
		Long id = itemService.saveItem(item);
		return edit(id, 1, model);
	}

	@RequestMapping(value = "delete", produces = "text/html; charset=UTF-8")
	@ResponseBody
	public String delete(Long id) {
		itemService.deleteItem(id);
		return "ok";
	}

}
