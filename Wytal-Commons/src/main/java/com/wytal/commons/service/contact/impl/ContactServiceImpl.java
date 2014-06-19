package com.wytal.commons.service.contact.impl;


import java.util.Collection;
import java.util.List;

import com.wytal.commons.contact.Contact;
import com.wytal.commons.service.contact.ContactDaoService;
import com.wytal.commons.service.contact.ContactService;

public class ContactServiceImpl implements ContactService {
	
	public void init(){}
	private ContactDaoService service;
	public void setContactDaoService(ContactDaoService service){
		this.service = service;
	}
	@Override
	public void store(long id, Collection<Contact> contacts) throws Exception {
		this.service.store(id,contacts);

	}

	@Override
	public Collection<Contact> get(long id) throws Exception {
		return this.service.get(id);

	}

	@Override
	public void delete(List<Long> contactids) throws Exception {
		this.service.delete(contactids);

	}

}
