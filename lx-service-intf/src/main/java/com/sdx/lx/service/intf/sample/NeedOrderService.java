package com.sdx.lx.service.intf.sample;

import java.io.File;
import java.util.List;

import com.sdx.lx.service.intf.sample.dto.NeedOrder;
import com.sdx.lx.service.intf.sample.dto.NeedOrderDto;
import com.sdx.lx.service.intf.sample.dto.NeedOrderItem;

public interface NeedOrderService {

	/**
	 * 详情列表
	 */
	List<NeedOrderDto> getNeedOrderListDetail(String keyword, Long startIndex, Long num);

	/**
	 * 列表数量
	 */
	Long getNeedOrderListCount(String keyword);

	/**
	 * 需货单
	 */
	NeedOrder getNeedOrder(Long id);

	/**
	 * 需货单
	 */
	NeedOrderDto getNeedOrderDetail(Long id);

	/**
	 * 需货单项
	 */
	List<NeedOrderItem> getNeedOrderItemList(Long needOrderId);

	/**
	 * 需货单项
	 */
	NeedOrderItem getNeedOrderItem(Long id);

	/**
	 * 保存需货单
	 */
	Long saveNeedOrder(NeedOrder order, Long[] itemId, String[] productName, String[] spec, Integer[] num,
			String[] itemNote);

	/**
	 * 删除需货单
	 */
	void deleteNeedOrder(Long id);

	/**
	 * 删除需货单项
	 */
	void deleteNeedOrderItem(Long id);

	/**
	 * 导出
	 */
	File exportNeedOrder();

}
