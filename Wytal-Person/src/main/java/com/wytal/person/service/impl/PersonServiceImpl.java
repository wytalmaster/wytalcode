package com.wytal.person.service.impl;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import org.slf4j.Logger;

import com.wytal.logging.factory.WytalLoggingFactory;
import com.wytal.person.Person;
import com.wytal.person.PersonSearchRequest;
import com.wytal.person.PersonSearchResponse;
import com.wytal.person.service.PersonDaoService;
import com.wytal.person.service.PersonService;
import com.wytal.util.exception.WytalException;
import com.wytal.util.exception.WytalExceptionFactory;

public class PersonServiceImpl  implements PersonService{
	private PersonDaoService service;
	private WytalExceptionFactory exceptionFactory;
	protected Logger logger = WytalLoggingFactory.getLogger(WytalLoggingFactory.PERSON_LOGGER);
	
	public void init(){
		
	}
	public void setPersonDaoService(PersonDaoService service){
		this.service = service;
	}
	
	public void setExceptionFactory(WytalExceptionFactory exceptionFactory){
		this.exceptionFactory = exceptionFactory;
	}
	
	@POST
	@PUT
	@Override
	@Path("/")
	public void add(Person person) throws WytalException{
		try {
			if(person.getId()>0){
				this.service.update(person);
			}
			else {
				this.service.add(person);
			}
		}
		catch(WytalException we){
			throw we;
		}
		catch(Exception ex){
			logger.error("WT9999:Unexpected error",ex);
			throw this.exceptionFactory.get("WT9999");
		}
	}
	
	
	@POST
	@Override
	@Path("/Update")
	public void update(Person person) throws WytalException{
		try {
			if(person.getId()>0){
				this.service.update(person);
			}
			else {
				this.service.add(person);
			}
		}
		catch(WytalException we){
			throw we;
		}
		catch(Exception ex){
			logger.error("WT9999:Unexpected error",ex);
			throw this.exceptionFactory.get("WT9999");
		}
	}
	
	@GET
	@Override
	@Path("/{id}")
	public Person getPerson(@PathParam("id")long person) throws WytalException{
		try {
			return this.service.getPerson(person);
		}
		catch(WytalException we){
			throw we;
		}
		catch(Exception ex){
			logger.error("WT9999:Unexpected error",ex);
			throw this.exceptionFactory.get("WT9999");
		}
	}
	
	@DELETE
	@Override
	@Path("/{id}")
	public void deletePerson(@PathParam("id")long person) throws WytalException{
		try {
			this.service.deletePerson(person);
		}
		catch(WytalException we){
			throw we;
		}
		catch(Exception ex){
			logger.error("WT9999:Unexpected error",ex);
			throw this.exceptionFactory.get("WT9999");
		}
	}
	
	@GET
	@Override
	@Path("/search")
	public PersonSearchResponse search(@QueryParam("lastName") String lastName,
					   @QueryParam("firstName")String firstName,
					   @QueryParam("dob")String dob,
					   @QueryParam("organizationId") int organizationid,
					   @QueryParam("sortKey") String sortKey,
					   @QueryParam("sortDirection") String sortType,
					   @QueryParam("offset") int offset,
					   @QueryParam("limit") int limit) throws WytalException {

		try {
			if(limit<=0){
				limit=10;
			}
			if(offset<=0){
				offset=0;
			}
			PersonSearchRequest psr = new PersonSearchRequest();
			psr.setFirstName(firstName);
			psr.setLastName(lastName);
			psr.setDob(dob);
			psr.setOffset(offset);
			psr.setLimit(limit);
			psr.setSortKey(sortKey);
			psr.setSortDir(sortType);
			psr.setOrganizationid(organizationid);
			return this.service.search(psr);
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
