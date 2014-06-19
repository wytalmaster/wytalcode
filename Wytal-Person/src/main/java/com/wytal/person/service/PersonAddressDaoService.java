package com.wytal.person.service;

import java.sql.Connection;
import java.util.Collection;
import java.util.List;

import com.wytal.person.PersonAddress;

public interface PersonAddressDaoService {
	public void store(long personid,Collection<PersonAddress> address) throws Exception; 
	public void store(long personid,Collection<PersonAddress> address,Connection conn) throws Exception; 
	public void delete(long personid,List<Integer> person_addressid,Connection conn) throws Exception ;
	public void delete(long personid,List<Integer> person_addressid) throws Exception;
	
	public Collection<PersonAddress> get(long personid,Connection conn) throws Exception;
	public Collection<PersonAddress> get(long personid) throws Exception;

}
