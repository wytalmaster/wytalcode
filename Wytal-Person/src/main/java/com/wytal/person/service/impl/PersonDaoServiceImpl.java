package com.wytal.person.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;

import com.wytal.commons.service.contact.ContactDaoService;
import com.wytal.logging.factory.WytalLoggingFactory;
import com.wytal.person.Person;
import com.wytal.person.Person.Gender;
import com.wytal.person.PersonItem;
import com.wytal.person.PersonSearchRequest;
import com.wytal.person.PersonSearchResponse;
import com.wytal.person.service.PersonAddressDaoService;
import com.wytal.person.service.PersonDaoService;
import com.wytal.person.service.PersonRelationDaoService;
import com.wytal.util.crypto.WytalCrypto;
import com.wytal.util.exception.WytalException;
import com.wytal.util.exception.WytalExceptionFactory;
import com.wytal.util.service.ServiceBase.ServiceResource;

public class PersonDaoServiceImpl extends ServiceResource implements PersonDaoService{
	private WytalExceptionFactory exceptionFactory;
	public void setExceptionFactory(WytalExceptionFactory factory){
		this.exceptionFactory = factory;
	}
	protected Logger logger = WytalLoggingFactory.getLogger(WytalLoggingFactory.PERSON_LOGGER);
	public Logger getLogger(){
		return logger;
	}
	
	private ContactDaoService contactDaoService;
	public void setPersonContactDaoService(ContactDaoService service){
		this.contactDaoService = service;
	}
	
	private PersonRelationDaoService relationDaoService;
	public void setPersonRelationDaoService(PersonRelationDaoService service){
		this.relationDaoService = service;
	}
	
	private PersonAddressDaoService addressDaoService;
	public void setPersonAddressDaoService(PersonAddressDaoService service){
		this.addressDaoService = service;
	}
	
	private WytalCrypto crypto;
	public void setCrypto(WytalCrypto crypto){
		this.crypto = crypto;
	}
	
	public void add(Person person) throws WytalException{
		Connection conn = null;
		try {
			conn = this.getConnection().getWytalDataSource();
			this.add(person,conn);
			conn.commit();
		}
		catch(Exception ex){
			ex.printStackTrace();
			this.getConnection().rollback(conn);
			logger.error("Error updating person",ex);
			throw this.exceptionFactory.get("WT9999");
			
		}
		finally{
			this.getConnection().closeConnection(conn);
		}
		
		
	}
	
	public void update(Person person) throws WytalException{
		Connection conn = null;
		try {
			conn = this.getConnection().getWytalDataSource();
			this.update(person,conn);
			conn.commit();
		}
		catch(Exception ex){
			this.getConnection().rollback(conn);
			logger.error("Error updating person",ex);
			throw this.exceptionFactory.get("WT9999");
			
		}
		finally{
			this.getConnection().closeConnection(conn);
		}
	}
	
	
	
	
	public Person getPerson(long person) throws WytalException{
		Connection conn = null;
		try {
			conn = this.getConnection().getWytalDataSource();
			return this.getPerson(person,conn);
		}
		finally{
			this.getConnection().closeConnection(conn);
		}
	}
	
	private Person getPerson(ResultSet rs ) throws SQLException{
		Person p = new Person();
		p.setId(rs.getLong(1));
		p.setOrganizationId(rs.getInt(2));
		p.setFirstName(rs.getString(3));
		p.setLastName(rs.getString(4));
		p.setMiddleName(rs.getString(5));
		p.setOtherName(rs.getString(6));
		p.setDob(rs.getDate(7));
		int gender = rs.getInt(8);
		for(Gender g : Gender.values()){
			if(g.ordinal()==gender){
				p.setGender(g);
				break;
			}
		}
		p.setNationalID(rs.getString(9));
		p.setSecretKey(rs.getString(10));
		p.setCreatedTime(rs.getTimestamp(11));
		p.setLastUpdated(rs.getTimestamp(12));
		return p;
		
		
	}
	
	public Person getPerson(long personid,Connection conn) throws WytalException{
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			ps = conn.prepareStatement(this.getProperty("104"));
			ps.setLong(1, personid);
			rs = ps.executeQuery();
			if(rs.next()){
				Person p = this.getPerson(rs);
				p.setContacts(this.contactDaoService.get(p.getId(),conn));
				p.setRelationShip(this.relationDaoService.get(p.getId(),conn));
				p.setContactAddress(this.addressDaoService.get(personid,conn));
				return p;
			}
			
			return null;
			
		}
		catch(Exception ex){
			logger.error("Error in getPerson",ex);
			throw this.exceptionFactory.get("WT9999");
		}
		finally{
			this.getConnection().releaseResources(rs, ps,null);
		}
		
	}
	
	public void deletePerson(long person) throws WytalException{
		Connection conn = null;
		try {
			conn = this.getConnection().getWytalDataSource();
			 this.deletePerson(person,conn);
		}
		finally{
			this.getConnection().closeConnection(conn);
		}
	}
	
	public void update(Person person,Connection conn) throws WytalException{
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(this.getProperty("102"));
			ps.setString(1,person.getFirstName());
			ps.setString(2,person.getLastName());
			ps.setString(3,person.getMiddleName());
			ps.setString(4,person.getOtherName());
			ps.setDate(5, new java.sql.Date(person.getDob().getTime()));
			ps.setInt(6, person.getGender().ordinal());
			ps.setString(7,person.getNationalID());
			ps.setLong(8,person.getId());
			ps.executeUpdate();
			this.contactDaoService.store(person.getId(),person.getContacts(),conn);
			this.relationDaoService.store(person.getId(),person.getRelationShip(),conn);
			this.addressDaoService.store(person.getId(), person.getContactAddress(),conn);
		}
		catch(WytalException we){
			throw we;
		}
		catch(Exception ex){
			this.getLogger().error("Unknown exception updating person ",ex);
			this.getExceptionFactory().get("WS9999");
		}
	}
	
	public void add(Person person,Connection conn) throws WytalException{
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			ps = conn.prepareStatement(this.getProperty("101"),Statement.RETURN_GENERATED_KEYS);
			ps.setLong(1, person.getOrganizationId());
			ps.setString(2,person.getFirstName());
			ps.setString(3,person.getLastName());
			ps.setString(4,person.getMiddleName());
			ps.setString(5,person.getOtherName());
			ps.setDate(6, new java.sql.Date(person.getDob().getTime()));
			ps.setInt(7, person.getGender().ordinal());
			ps.setString(8,person.getNationalID());
			ps.setString(9,new String(crypto.enrypt(UUID.randomUUID().toString()),"UTF-8"));
			ps.executeUpdate();
			rs =ps.getGeneratedKeys();
			if(rs.next()){
				person.setId(rs.getLong(1));
			}
			
			this.contactDaoService.store(person.getId(),person.getContacts(),conn);
			this.relationDaoService.store(person.getId(),person.getRelationShip(),conn);
			this.addressDaoService.store(person.getId(), person.getContactAddress(),conn);
			
		}
		catch(WytalException we){
			throw we;
		}
		catch(Exception ex){
			this.getLogger().error("Unknown exception adding person ",ex);
			this.getExceptionFactory().get("WS9999");
		}
		finally {
			this.getConnection().releaseResources(rs, ps,null);
		}
		
	}
	
	
	
	public void deletePerson(long personid,Connection conn) throws WytalException{
		PreparedStatement ps  = null;
		try{
			ps = conn.prepareStatement(this.getProperty("103"));
			ps.setLong(1, personid);
			ps.executeUpdate();
		}
		catch(Exception ex){
			this.getLogger().error("Unknown exception adding person ",ex);
			this.getExceptionFactory().get("WS9999");
		}
		finally{
			this.getConnection().closeStatement(ps);
		}
	}
	
	public PersonSearchResponse search(PersonSearchRequest request) throws WytalException{
		String sql = this.getProperty("105");
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn =null;
		try {
			boolean orgInSearch = false;
			boolean dobInSearch = false;
			boolean lastNameInSearch = false;
			boolean firstNameInSearch = false;
			String appendQuery = " ";
			if(request.getOrganizationid() > 0){
				orgInSearch = true;
				if(request.getDob() != null){
					dobInSearch = true;
					if(request.getLastName() != null){
						lastNameInSearch=true;
						//105K
						appendQuery = this.getProperty("105J");
					}
					else  if(request.getFirstName() != null){
						firstNameInSearch = true;
						//105J
						appendQuery = this.getProperty("105K");
					}
					else {
						appendQuery = this.getProperty("105I");
					}
				}
				else {
					if(request.getLastName() != null){
						lastNameInSearch=true;
						appendQuery = this.getProperty("105E");
						//105E
					}
					else  if(request.getFirstName() != null){
						firstNameInSearch = true;
						appendQuery = this.getProperty("105F");
						//105F
					}
					else {
						//I05A
						appendQuery = this.getProperty("105A");
					}
				}
					
			}
			else {
				if(request.getDob() != null){
					dobInSearch = true;
					if(request.getLastName() != null){
						lastNameInSearch = true;
						appendQuery = this.getProperty("105G");
						//105G
					}
					else  if(request.getFirstName() != null){
						firstNameInSearch = true;
						appendQuery = this.getProperty("105H");
						//105H
					}
					else {
						appendQuery = this.getProperty("105D");
						//I05D
					}
				}
				else {
					if(request.getLastName() != null){
						lastNameInSearch = true;
						appendQuery = this.getProperty("105B");
						//105B
					}
					else  if(request.getFirstName() != null){
						firstNameInSearch = true;
						appendQuery = this.getProperty("105C");
						//105C
					}
					else {
						// Empty String
					}
				}
			}
			sql = String.format(sql, appendQuery,request.getSortKey().name(),request.getSortDir().name());
			//System.out.println("Search sql" +sql);
			conn = this.getConnection().getWytalDataSource();
			ps = conn.prepareStatement(sql);
			int n= 1;
			if(orgInSearch){
				ps.setInt(n++, request.getOrganizationid());
			}
			if(dobInSearch){
				ps.setDate(n++, new java.sql.Date(request.getDob().getTime()));
			}
			if(lastNameInSearch){
				ps.setString(n++, request.getLastName()+"%");
			}
			if(firstNameInSearch){
				ps.setString(n++, request.getFirstName()+"%");
			}
			
			ps.setInt(n++, request.getOffset());
			ps.setInt(n++,  request.getLimit()+1);
			rs = ps.executeQuery();
			List<PersonItem> plist = new ArrayList<>();
			//SELECT P.ID,O.ORGID,O.NAME,P.FIRSTNAME, P.LASTNAME,P.DOB,P.GENDER 
			int c=0;
			while(rs.next()){
				PersonItem p = new PersonItem();
				p.setId(rs.getLong(1));
				p.setOrganizationId(rs.getInt(2));
				p.setOrganizationName(rs.getString(3));
				p.setFirstName(rs.getString(4));
				p.setLastName(rs.getString(5));
				p.setDob(rs.getDate(6));
				int gender  = rs.getInt(7);
				for(Gender g : Gender.values()){
					if(g.ordinal()==gender){
						p.setGender(g);
						break;
					}
				}
				plist.add(c,p);
			}
			PersonSearchResponse psr = new PersonSearchResponse();
			psr.setOffset(request.getOffset());
			psr.setLimit(request.getLimit());
			psr.setMore((plist.size()>request.getLimit())?true:false);
			psr.setSortKey(request.getSortKey().name());
			psr.setSortDir(request.getSortDir().name());
			if(psr.isMore()){
				plist.remove(plist.size()-1);
			}
			psr.setItems(plist);
			return psr;

		}catch(Exception ex){
			ex.printStackTrace();
			logger.error("Error in querying the person details",ex);
			throw this.exceptionFactory.get("WT9999");
		}
		finally{
			this.getConnection().releaseResources(rs, ps,conn);
		}
			
	}
	

}
