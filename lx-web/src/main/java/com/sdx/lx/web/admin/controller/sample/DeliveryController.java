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

import com.sdx.lx.service.intf.sample.DeliveryOrderService;
import com.sdx.lx.service.intf.sample.NeedOrderService;
import com.sdx.lx.service.intf.sample.dto.DeliveryOrder;
import com.sdx.lx.service.intf.sample.dto.NeedOrder;
import com.sdx.lx.service.intf.sample.dto.NeedOrderItem;
import com.sdx.lx.web.util.ControllerUtil;
import com.sdx.lx.web.util.MobilePageParam;

@Controller
@RequestMapping("/deliver")
public class DeliveryController {

	@Autowired
	NeedOrderService needOrderService;

	@Autowired
	DeliveryOrderService deliveryOrderService;

	@RequestMapping("/deliverlist")
	public String needlist(String keyword, MobilePageParam page, Model model) {
		List<DeliveryOrder> list = deliveryOrderService.getDeliveryOrderList(null, keyword, page.getStartIndex(),
				page.getMaxCount());
		Long count = deliveryOrderService.getDeliveryOrderListCount(null, keyword);
		model.addAttribute("list", list);

		model.addAttribute("keyword", keyword);
		model.addAttribute("PageParam", ControllerUtil.getPageParam(count, page));
		return "/deliver/deliverlist.ftl";
	}

	@RequestMapping("/add")
	public String add(Long needOrderItemId, Integer success, Model model) {
		NeedOrderItem item = needOrderService.getNeedOrderItem(needOrderItemId);
		NeedOrder need = needOrderService.getNeedOrder(item.getNeedOrderId());
		List<DeliveryOrder> deliverList = deliveryOrderService.getDeliveryOrderList(needOrderItemId, null, null, null);
		model.addAttribute("deliverList", deliverList);
		model.addAttribute("item", item);
		model.addAttribute("need", need);
		model.addAttribute("success", success);
		return "/deliver/deliveradd.ftl";
	}

	@RequestMapping("/edit")
	public String toUpdate(Long id, Integer success, Model model) {
		DeliveryOrder deliver = deliveryOrderService.getDeliveryOrder(id);
		NeedOrderItem item = needOrderService.getNeedOrderItem(deliver.getNeedOrderItemId());
		NeedOrder need = needOrderService.getNeedOrder(deliver.getNeedOrderId());
		model.addAttribute("deliver", deliver);
		model.addAttribute("item", item);
		model.addAttribute("need", need);
		model.addAttribute("success", success);
		return "/deliver/deliveredit.ftl";
	}

	@RequestMapping("/deliverDetail")
	public String deliverDetail(Long needOrderItemId, Model model) {
		NeedOrderItem item = needOrderService.getNeedOrderItem(needOrderItemId);
		NeedOrder need = needOrderService.getNeedOrder(item.getNeedOrderId());
		List<DeliveryOrder> deliverList = deliveryOrderService.getDeliveryOrderList(needOrderItemId, null, null, null);
		model.addAttribute("deliverList", deliverList);
		model.addAttribute("item", item);
		model.addAttribute("need", need);
		return "/deliver/deliverDetail.ftl";
	}

	@RequestMapping("/save")
	public String save(Long needOrderItemId, DeliveryOrder deliveryOrder, Model model) {
		deliveryOrderService.saveDeliveryOrder(deliveryOrder);
		return add(needOrderItemId, 1, model);
	}

	@RequestMapping("/update")
	public String update(DeliveryOrder deliveryOrder, Model model) {
		deliveryOrderService.updateDeliveryOrder(deliveryOrder);
		return toUpdate(deliveryOrder.getId(), 1, model);
	}

	@RequestMapping("/export")
	public void export(HttpServletResponse response, Model model) throws FileNotFoundException, IOException {
		File file = deliveryOrderService.exportDeliverOrder();
		if (file != null) {
			response.setHeader("Content-Disposition", "attachment; filename=2.xlsx");
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
