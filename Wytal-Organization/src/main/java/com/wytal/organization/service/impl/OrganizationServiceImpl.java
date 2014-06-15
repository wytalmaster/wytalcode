package com.wytal.organization.service.impl;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import org.slf4j.Logger;

import com.wytal.commons.org.OrganizationType;
import com.wytal.commons.seed.service.SeedDataService;
import com.wytal.logging.factory.WytalLoggingFactory;
import com.wytal.organization.Organization;
import com.wytal.organization.OrganizationSearchRequest;
import com.wytal.organization.OrganizationSearchResponse;
import com.wytal.organization.service.OrganizationDaoService;
import com.wytal.organization.service.OrganizationService;
import com.wytal.util.exception.WytalException;
import com.wytal.util.exception.WytalExceptonFactory;

public class OrganizationServiceImpl implements OrganizationService{
	
	public void init(){
		
	}
	protected Logger logger = WytalLoggingFactory.getLogger(WytalLoggingFactory.ORG_LOGGER);
	private WytalExceptonFactory exceptionFactory;
	public void setExceptionFactory(WytalExceptonFactory factory){
		this.exceptionFactory = factory;
	}
	
	private OrganizationDaoService service;
	
	public void setOrganizationDaoService(OrganizationDaoService service){
		this.service = service;
	}
	
	
	private SeedDataService seedDataService;
	
	public void setSeedDataService(SeedDataService service){
		this.seedDataService = service;
	}

	@POST
	@PUT
	@Override
	@Path("/")
	public void add(Organization org) throws WytalException{
		try {
			this.validate(org);
			this.service.store(org);
		}
		catch(WytalException we){
			throw we;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	@POST
	@Override
	@Path("/Update")
	public void uddate(Organization org) throws WytalException {
		try {
			this.validate(org);
			this.service.store(org);
		}
		catch(WytalException we){
			throw we;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	@GET
	@Override
	@Path("/{id}")
	public Organization getOrganization(@PathParam("id") int organization) throws WytalException {
		try {
			return this.service.getOrganization(organization);
		}
		catch(Exception e) {
		
		}
		return null;
	}

	@GET
	@Override
	@Path("/GetParent/{id}")
	public Organization getParentOrganization(@PathParam("id") int organization) throws WytalException {
		try {
			return this.service.getParentOrganization(organization);
		}
		catch(Exception e) {
		
		}
		return null;
	}

	@GET
	@Override
	@Path("/GetChildren/{id}")
	public List<Organization> getChildOrganization(@PathParam("id") int organization) throws WytalException {
		try {
			return this.service.getChildOrganization(organization);
		}
		catch(Exception e) {
		
		}
		return null;
	}

	
	@DELETE
	@Override
	@Path("/{id}")
	public  void deleteOrganization(@PathParam("id") int organization) throws WytalException {
		try {
			 this.service.deleteOrganization(organization);
		}
		catch(WytalException we){
			throw we;
		}
		catch(Exception e) {
			logger.error("Unknown exception in deleting the organization",e);
			throw this.exceptionFactory.get("WT9999");
		}
		
	}
	
	
	private void validate(Organization org) throws WytalException{
		if (org.getName()==null|| "".equals(org.getName())){
			//Organization name missing
			throw this.exceptionFactory.get("OR0001");
		}
		
		
		boolean isValid =false;
		if(org.getType() ==null){
			throw exceptionFactory.get("OR0004","Invalid Organization type");
		}
		for(OrganizationType t : this.seedDataService.getOrganizationTypes()){

			if(org.getType().getId() ==t.getId()){
				isValid =true;
				break;
			}
		}
		if(!isValid) {
			throw exceptionFactory.get("OR0004","Invalid Organization type");
		}
		if(org.getParentOrganization() != null && !this.service.isOrganizationValid(org.getParentOrganization())){
			//Invalid Parent Organization
			throw this.exceptionFactory.get("OR0002");
		}
		
	}
	
	@GET
	@Path("/search")
	public OrganizationSearchResponse search(@QueryParam("typeid")int typeid, 
											@QueryParam("name")String name,
											@QueryParam("offset")int offset, 
											@QueryParam("limit")int limit) throws WytalException {
		try {
			OrganizationSearchRequest osr = new OrganizationSearchRequest();
			if(typeid>0){
				for(OrganizationType t : this.seedDataService.getOrganizationTypes()){
					if(typeid ==t.getId()){
						osr.setType(t);
						break;
					}
				}
				if(osr.getType()==null){
					throw exceptionFactory.get("OR0004","Invalid Organization type");
				}
			}
			if(name != null && !"".equals(name)){
				osr.setName(name);
			}
			osr.setOffset(offset);
			osr.setLimit((limit<=0)?10:limit);
			return this.service.search(osr);
		}
		catch(Exception e) {
				
		}
		return null;
	}
	
	

}
