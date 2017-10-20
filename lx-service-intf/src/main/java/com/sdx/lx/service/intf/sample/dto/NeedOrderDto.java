/*
 * Copyright (C), 2013-2014, 
 * FileName: NeedOrder.java
 * Author:   zhuliang
 * Date:     2014年10月16日 下午1:30:32
 */
package com.sdx.lx.service.intf.sample.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * 实体类需货单
 * 
 * @author zhuliang
 */
public class NeedOrderDto extends NeedOrder {
	/**
	 * Serial UID
	 */
	private static final long serialVersionUID = 1L;
	
	private List<NeedOrderItem> items = new ArrayList<NeedOrderItem>();;

	/**
	 * @return the items
	 */
	public List<NeedOrderItem> getItems() {
		return items;
	}

	/**
	 * @param items
	 *            the items to set
	 */
	public void setItems(List<NeedOrderItem> items) {
		this.items = items;
	}

}
