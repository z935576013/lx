package com.sdx.lx.service.intf.sample;

import java.util.List;

import com.sdx.lx.service.intf.sample.dto.ProductInfo;

public interface ProductInfoService {

	/**
	 * 保存
	 */
	Long saveProduct(ProductInfo product);

	/**
	 * 礼品
	 */
	ProductInfo get(Long id);

	/**
	 * 列表
	 */
	List<ProductInfo> getProductList(String keyword, Long startIndex, Long num);

	/**
	 * 数量
	 */
	Long getProductListCount(String keyword);

	/**
	 * 删除
	 */
	void deleteProduct(Long id);

}
