package com.wytal.person.service.impl;

import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;

import com.wytal.logging.factory.WytalLoggingFactory;
import com.wytal.person.PersonAddress;
import com.wytal.person.service.PersonAddressDaoService;
import com.wytal.person.service.PersonAddressService;
import com.wytal.util.exception.WytalException;
import com.wytal.util.exception.WytalExceptionFactory;

public class PersonAddressServiceImpl implements PersonAddressService {
	
	public void init(){
		
	}
	
	protected Logger logger = WytalLoggingFactory.getLogger(WytalLoggingFactory.PERSON_LOGGER);
	private PersonAddressDaoService service;
	public void setPersonAddressDaoService(PersonAddressDaoService service){
		this.service = service;
	}
	
	private WytalExceptionFactory exceptionFactory;
	public void setExceptionFactory(WytalExceptionFactory exceptionFactory){
		this.exceptionFactory = exceptionFactory;
	}
	public void store(long personid,Collection<PersonAddress> address) throws WytalException{
		try{
			this.service.store(personid, address);
		}
		catch(WytalException we){
			throw we;
		}
		catch(Exception ex){
			throw exceptionFactory.get("WT9999");
		}
	}
	public void delete(long personid,List<Integer> person_addressid) throws WytalException{
		try{
			this.service.delete(personid, person_addressid);
		}
		catch(WytalException we){
			throw we;
		}
		catch(Exception ex){
			throw exceptionFactory.get("WT9999");
		}
	}
	public Collection<PersonAddress> get(long personid) throws WytalException{
		try{
			return this.service.get(personid);
		}
		catch(WytalException we){
			throw we;
		}
		catch(Exception ex){
			throw exceptionFactory.get("WT9999");
		}
	}
	

}
