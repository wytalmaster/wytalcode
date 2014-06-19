package com.wytal.person.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;

import com.wytal.commons.address.Address;
import com.wytal.commons.address.AddressType;
import com.wytal.commons.address.service.AddressDaoService;
import com.wytal.commons.seed.service.SeedDataService;
import com.wytal.logging.factory.WytalLoggingFactory;
import com.wytal.person.PersonAddress;
import com.wytal.person.service.PersonAddressDaoService;
import com.wytal.util.service.ServiceBase.ServiceResource;

public class PersonAddressDaoServiceImpl extends ServiceResource implements PersonAddressDaoService {

	protected Logger logger = WytalLoggingFactory.getLogger(WytalLoggingFactory.PERSON_LOGGER);
	
	@Override
	protected Logger getLogger() {
		return logger;
	}
	
	private SeedDataService seedDataService;
	public void setSeedDataService(SeedDataService service){
		this.seedDataService = service;
	}
	
	private AddressDaoService addressService;
	public void setAddressDaoService(AddressDaoService service){
		this.addressService = service;
	}
	
	public void store(long personid,Collection<PersonAddress> address) throws Exception {
		Connection conn = null;
		try {
			conn = this.getConnection().getWytalDataSource();
			this.store(personid,address,conn);
			
		}
		catch(Exception ex){
			logger.error("Exception storing the address",ex);
			throw ex;
		}
		finally{
			this.getConnection().closeConnection(conn);
		}
	}
	
	public void delete(long personid,List<Integer> person_addressid) throws Exception {
		Connection conn = null;
		try {
			conn = this.getConnection().getWytalDataSource();
			this.delete(personid, person_addressid, conn);
			
		}
		catch(Exception ex){
			logger.error("Exception deleting the address",ex);
			throw ex;
		}
		finally{
			this.getConnection().closeConnection(conn);
		}
	}
	
	public Collection<PersonAddress> get(long personid) throws Exception{
		Connection conn = null;
		try {
			conn = this.getConnection().getWytalDataSource();
			return this.get(personid,conn);
		}
		catch(Exception ex){
			logger.error("Exception querying the address",ex);
			throw ex;
		}
		finally{
			this.getConnection().closeConnection(conn);
		}
	}
	
	
	
	
	
	public void store(long personid,Collection<PersonAddress> address,Connection conn) throws Exception {
		if(address==null||address.size()==0){ return;}
		List<PersonAddress> addList = new ArrayList<>();
		List<PersonAddress> updateList = new ArrayList<>();
		for(PersonAddress a : address){
			if(a.getAddress() != null){
				if(a.getAddress().getAddressId() >0){
					updateList.add(a);
				}
				else {
					addList.add(a);
				}
			}
		}
		add(personid,addList,conn);
		update(personid, updateList,conn);
		
	}
	
	
	public Collection<PersonAddress> get(long personid, Connection conn) throws Exception{
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			List<PersonAddress> paList = new ArrayList<>();
			Map<Long,Address> addressMap = this.addressService.getAllAddress(personid,conn);
			ps= conn.prepareStatement(this.getProperty("103"));
			ps.setLong(1, personid);
			rs = ps.executeQuery();
			while(rs.next()){
				PersonAddress pa =  new PersonAddress();
				pa.setId(rs.getLong(1));
				int addressType = rs.getInt(2);
				for(AddressType at : this.seedDataService.getAddressType()){
					if(at.getId()==addressType){
						pa.setType(at);
						break;
					}
				}
				long addressId = rs.getLong(3);
				pa.setAddress(addressMap.get(addressId));
				pa.setPrimary(rs.getBoolean(4));
				paList.add(pa);
			}
			return paList;
		}
		catch(Exception ex){
			logger.error("Exception querying address",ex);
			throw ex;
		}
		finally{
			this.getConnection().releaseResources(rs, ps,null);
		}
	}
	
	
	
	public void delete(long personid,List<Integer> person_addressid,Connection conn) throws Exception {
		PreparedStatement ps = null;
		ResultSet rs  = null;
		try {
			ps = conn.prepareStatement(this.getProperty("104"));
			for(long pid : person_addressid){
				ps.clearParameters();
				ps.setLong(1, personid);
				ps.setLong(2, pid);
				ps.addBatch();
			}
			ps.executeBatch();
					
		}
		catch(Exception ex){
			logger.error("Exception querying address",ex);
			throw ex;
		}
		finally{
			this.getConnection().releaseResources(rs, ps,null);
		}
	}
	
	private void add(long personid,List<PersonAddress> address,Connection conn) throws Exception {
		if(address.size()==0){ return;}
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			System.out.println(this.getProperty("101"));
			ps = conn.prepareStatement(this.getProperty("101"));
			for(PersonAddress a : address){
				this.addressService.storeAddress(a.getAddress(), conn);
				
				ps.setLong(1, personid);
				ps.setInt(2, a.getType().getId());
				ps.setLong(3, a.getAddress().getAddressId());
				ps.setBoolean(4, a.isPrimary());
				ps.addBatch();
			}
			ps.executeBatch();
		}
		catch(Exception ex){
			logger.error("Error in adding person contact",ex);
			throw ex;
		}
		finally{
			this.getConnection().releaseResources(rs, ps,null);
		}
		
	}
	
	
	private void update(long personid,List<PersonAddress> address,Connection conn) throws Exception {
		if(address.size()==0){ return;}
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(this.getProperty("102"));
			for(PersonAddress a : address){
				this.addressService.storeAddress(a.getAddress(), conn);
				ps.setInt(1, a.getType().getId());
				ps.setLong(2, a.getAddress().getAddressId());
				ps.setBoolean(3, a.isPrimary());
				ps.setLong(4, personid);
				ps.addBatch();
			}
			ps.executeBatch();
		}
		catch(Exception ex){
			logger.error("Error in updating person contact",ex);
			throw ex;
		}
		finally{
			this.getConnection().releaseResources(rs, ps,null);
		}
		
	}
	
	
	
	
	
	//101=INSERT INTO PERSON_ADDRESS ( PERSONID,ADDERSSTYPEID,ADDRESSID,ISPRIMARY,CREATED,UPDATED) VALUES(?,?,?,?,NOW(),NOW())
	//102=UPDATE PERSON_ADDRESS SET ADDRESSTYPEID=?,ISPRIMARY=?,UPDATED=NOW() WHERE PESRSON_ADDRESS_ID=?
	


}
