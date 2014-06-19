package com.wytal.commons.service.contact;

import java.sql.Connection;
import java.util.Collection;
import java.util.List;

import com.wytal.commons.contact.Contact;

public interface ContactDaoService {
	public  void store(long id,Collection<Contact> contacts, Connection conn) throws Exception;
	public  void store(long id,Collection<Contact> org) throws Exception;
	public  Collection<Contact> get(long id) throws Exception;
	public  Collection<Contact>  get(long id, Connection conn) throws Exception;
	public  void delete(List<Long> contactids) throws Exception;
	public  void delete(List<Long> contactids, Connection conn);


}