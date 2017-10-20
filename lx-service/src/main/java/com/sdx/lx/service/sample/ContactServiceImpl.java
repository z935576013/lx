package com.sdx.lx.service.sample;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sdx.lx.common.dal.DalClient;
import com.sdx.lx.common.utils.BeanCopyUtils;
import com.sdx.lx.service.intf.sample.ContactService;
import com.sdx.lx.service.intf.sample.dto.Contact;

@Service
public class ContactServiceImpl implements ContactService {

	@Autowired
	DalClient dalClient;

	@Override
	public List<Contact> getContactList() {
		Map<String, Object> map = new HashMap<String, Object>();
		return dalClient.queryForList("CONTACT.SELECT_BY_FIELDS", map, Contact.class);
	}
	@Override
	public Contact getContact(Long id) {
		if (id == null) {
			return null;
		}
		Contact contact = new Contact();
		contact.setId(id);
		contact = dalClient.find(Contact.class, contact);
		if (contact == null) {
			return null;
		} else {
			contact.setId(id);
			return contact;
		}
	}

	@Override
	public Long saveContact(Contact contact) {
		Long id = null;
		if (contact != null) {
			id = contact.getId();
			if (id != null) {
				Contact old = getContact(id);
				BeanCopyUtils.copyProperties(contact, old);
				dalClient.merge(old);
			} else {
				id = dalClient.persist(contact).longValue();
			}
		}
		return id;
	}

	@Override
	public void deleteContact(Long id) {
		Contact contact = getContact(id);
		if (contact != null) {
			dalClient.remove(contact);
		}
	}

}
