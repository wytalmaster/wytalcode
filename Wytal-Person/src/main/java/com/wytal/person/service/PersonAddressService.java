package com.wytal.person.service;

import java.util.Collection;
import java.util.List;

import com.wytal.person.PersonAddress;
import com.wytal.util.exception.WytalException;

public interface PersonAddressService {
	public void store(long personid,Collection<PersonAddress> address) throws WytalException; 
	public void delete(long personid,List<Integer> person_addressid) throws WytalException;
	public Collection<PersonAddress> get(long personid) throws WytalException;

}
