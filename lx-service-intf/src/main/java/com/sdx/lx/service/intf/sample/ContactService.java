package com.sdx.lx.service.intf.sample;

import java.util.List;

import com.sdx.lx.service.intf.sample.dto.Contact;

public interface ContactService {

	/**
	 * 列表
	 */
	List<Contact> getContactList();

	Contact getContact(Long id);

	/**
	 * 保存
	 */
	Long saveContact(Contact item);

	/**
	 * 删除
	 */
	void deleteContact(Long id);
}
