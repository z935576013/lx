package com.sdx.lx.service.sample;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sdx.lx.common.dal.DalClient;
import com.sdx.lx.common.utils.BeanCopyUtils;
import com.sdx.lx.service.intf.sample.ItemService;
import com.sdx.lx.service.intf.sample.dto.Item;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	DalClient dalClient;

	@Override
	public List<Item> getItemList() {
		Map<String, Object> map = new HashMap<String, Object>();
		return dalClient.queryForList("ITEM.SELECT_BY_FIELDS", map, Item.class);
	}

	@Override
	public Item getItem(Long id) {
		if (id == null) {
			return null;
		}
		Item item = new Item();
		item.setId(id);
		item = dalClient.find(Item.class, item);
		if (item == null) {
			return null;
		} else {
			item.setId(id);
			return item;
		}
	}

	@Override
	public Long saveItem(Item item) {
		Long id = null;
		if (item != null) {
			id = item.getId();
			if (id != null) {
				Item old = getItem(id);
				BeanCopyUtils.copyProperties(item, old);
				dalClient.merge(old);
			} else {
				id = dalClient.persist(item).longValue();
			}
		}
		return id;
	}

	@Override
	public void deleteItem(Long id) {
		Item item = getItem(id);
		if (item != null) {
			dalClient.remove(item);
		}
	}

}
