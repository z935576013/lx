package com.sdx.lx.service.intf.sample;

import java.io.File;
import java.util.List;

import com.sdx.lx.service.intf.sample.dto.DeliveryOrder;

public interface DeliveryOrderService {

	/**
	 * 列表
	 */
	List<DeliveryOrder> getDeliveryOrderList(Long needOrderItemId, String keyword, Long startIndex, Long num);

	/**
	 * 列表数量
	 */
	Long getDeliveryOrderListCount(Long needOrderItemId, String keyword);

	/**
	 * 发货单
	 */
	DeliveryOrder getDeliveryOrder(Long id);

	/**
	 * 保存发货单
	 */
	void saveDeliveryOrder(DeliveryOrder deliveryOrder);

	/**
	 * 修改发货单
	 */
	void updateDeliveryOrder(DeliveryOrder deliveryOrder);

	/**
	 * 导出
	 */
	File exportDeliverOrder();
}
