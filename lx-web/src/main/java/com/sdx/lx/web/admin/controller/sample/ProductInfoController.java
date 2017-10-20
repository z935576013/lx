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

import com.sdx.lx.service.intf.sample.ProductInfoService;
import com.sdx.lx.service.intf.sample.dto.ProductInfo;
import com.sdx.lx.web.util.ControllerUtil;
import com.sdx.lx.web.util.MobilePageParam;

@Controller
@RequestMapping("/product")
public class ProductInfoController {

	@Autowired
	ProductInfoService productInfoService;

	@RequestMapping("/productlist")
	public String needlist(String keyword, MobilePageParam page, Model model) {
		List<ProductInfo> list = productInfoService.getProductList(keyword, page.getStartIndex(), page.getMaxCount());
		Long count = productInfoService.getProductListCount(keyword);
		model.addAttribute("list", list);

		model.addAttribute("keyword", keyword);
		model.addAttribute("PageParam", ControllerUtil.getPageParam(count, page));
		return "/product/productlist.ftl";
	}

	@RequestMapping("/edit")
	public String edit(Long id, Integer success, Model model) {
		ProductInfo product = productInfoService.get(id);
		if (product == null) {
			model.addAttribute("product", new ProductInfo());
		} else {
			model.addAttribute("product", product);
		}
		model.addAttribute("success", success);
		return "/product/productedit.ftl";
	}

	@RequestMapping("/save")
	public String save(ProductInfo product, Model model) {
		Long id = productInfoService.saveProduct(product);
		return edit(id, 1, model);
	}

	@RequestMapping(value = "delete", produces = "text/html; charset=UTF-8")
	@ResponseBody
	public String delete(HttpServletRequest request, Long id) {
		productInfoService.deleteProduct(id);
		return "ok";
	}

	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		CustomDateEditor editor = new CustomDateEditor(df, true);
		binder.registerCustomEditor(Date.class, editor);
	}

}
