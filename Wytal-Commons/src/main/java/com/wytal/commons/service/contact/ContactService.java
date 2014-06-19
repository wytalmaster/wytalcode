package com.wytal.commons.service.contact;



import java.util.Collection;
import java.util.List;

import com.wytal.commons.contact.Contact;

public interface ContactService {
	public  void store(long id, Collection<Contact> contacts) throws Exception;
	public  Collection<Contact> get(long id) throws Exception;
	public  void delete(List<Long> contactids) throws Exception;

}

