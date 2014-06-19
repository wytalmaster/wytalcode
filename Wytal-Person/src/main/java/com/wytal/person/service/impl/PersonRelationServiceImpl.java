package com.wytal.person.service.impl;

import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;

import com.wytal.logging.factory.WytalLoggingFactory;
import com.wytal.person.PersonRelation;
import com.wytal.person.service.PersonRelationService;
import com.wytal.util.exception.WytalException;
import com.wytal.util.exception.WytalExceptionFactory;

public class PersonRelationServiceImpl implements PersonRelationService{
	protected Logger logger = WytalLoggingFactory.getLogger(WytalLoggingFactory.PERSON_LOGGER);
	
	private WytalExceptionFactory exceptionFactory;
	public void setExceptionFactory(WytalExceptionFactory exceptionFactory){
		this.exceptionFactory = exceptionFactory;
	}

	private PersonRelationDaoServiceImpl personRelationService;
	public void setPersonRelationDaoService(PersonRelationDaoServiceImpl service){
		this.personRelationService = service;
	}
	public void init(){
		
	}
	
	@Override
	public void store(long personid,Collection<PersonRelation> relations) throws WytalException{
		try {
			this.personRelationService.store(personid,relations);
		}
		catch(WytalException we){
			throw we;
		}
		catch(Exception ex){
			logger.error("WT9999:Unexpected error",ex);
			throw this.exceptionFactory.get("WT9999");
		}
	}
	
	@Override
	public Collection<PersonRelation> get(long personid) throws WytalException{
		try {
			return this.personRelationService.get(personid);
		}
		catch(WytalException we){
			throw we;
		}
		catch(Exception ex){
			logger.error("WT9999:Unexpected error",ex);
			throw this.exceptionFactory.get("WT9999");
		}
	}
	
	@Override
	public void delete(long personid, List<Long> relationId) throws WytalException{
		try {
			this.personRelationService.delete(personid,relationId);
		}
		catch(WytalException we){
			throw we;
		}
		catch(Exception ex){
			logger.error("WT9999:Unexpected error",ex);
			throw this.exceptionFactory.get("WT9999");
		}
	}

}
