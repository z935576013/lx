package com.sdx.lx.service.intf.sample;

import java.util.List;

import com.sdx.lx.service.intf.sample.dto.Item;

public interface ItemService {

	/**
	 * 列表
	 */
	List<Item> getItemList();

	Item getItem(Long id);

	/**
	 * 保存
	 */
	Long saveItem(Item item);

	/**
	 * 删除
	 */
	void deleteItem(Long id);

}
