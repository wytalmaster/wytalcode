package com.wytal.organization.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;

import com.wytal.commons.address.service.AddressDaoService;
import com.wytal.commons.org.OrganizationType;
import com.wytal.commons.seed.service.SeedDataService;
import com.wytal.commons.service.contact.ContactDaoService;
import com.wytal.logging.factory.WytalLoggingFactory;
import com.wytal.organization.Organization;
import com.wytal.organization.OrganizationItem;
import com.wytal.organization.OrganizationSearchRequest;
import com.wytal.organization.OrganizationSearchResponse;
import com.wytal.organization.service.OrganizationDaoService;
import com.wytal.util.exception.WytalExceptionFactory;
import com.wytal.util.service.ServiceBase.ServiceResource;

public class OrganizationDaoServiceImpl extends ServiceResource implements OrganizationDaoService {

	protected Logger logger = WytalLoggingFactory.getLogger(WytalLoggingFactory.ORG_LOGGER);
	
	
	private AddressDaoService addressDaoService;
	private ContactDaoService orgContactService;
	
	public void setAddressDaoService(AddressDaoService service){
		this.addressDaoService = service;
	}
	
	public void setOrganizationContactDaoService(ContactDaoService service){
		this.orgContactService = service;
	}
	
	
	private WytalExceptionFactory exceptionFactory;
	public void setExceptionFactory(WytalExceptionFactory factory){
		this.exceptionFactory = factory;
	}
	
	private SeedDataService seedDataService;
	public void setSeedDataService(SeedDataService service){
		this.seedDataService = service;
	}
	
	@Override
	protected Logger getLogger() {
		return this.logger;
	}
	
	@Override
	public void store(Organization org) throws Exception{
		Connection conn = this.getConnection().getWytalDataSource();
		try {
			store(org,conn);
			conn.commit();
		}
		finally{
			conn.rollback();
			this.getConnection().closeConnection(conn);
		}		
	}
	
	@Override
	public Organization getOrganization(long id) throws Exception{
		Connection conn = this.getConnection().getWytalDataSource();
		try {
			return getOrganization(id,conn);
		}
		finally{
			this.getConnection().closeConnection(conn);
		}		
	}
	
	@Override
	public boolean deleteOrganization(long id) throws Exception{
		Connection conn = this.getConnection().getWytalDataSource();
		try {
			List<Integer> ids = this.getChildOrganizationID(id,conn);
			if(ids != null && ids.size()>0){
				logger.error("OR0003:Cannot delete organization, child record exists:"+ id);
				throw  this.exceptionFactory.get("OR0003");
			}
			
			return deleteOrganization(id,conn);
		}
		finally{
			this.getConnection().closeConnection(conn);
		}		
	}
	
	
	@Override
	public Organization getParentOrganization(int organization) throws Exception {
		Connection conn = this.getConnection().getWytalDataSource();
		try {
			return getParentOrganization(organization,conn);
		}
		finally{
			this.getConnection().closeConnection(conn);
		}
	}

	

	@Override
	public List<Organization> getChildOrganization(int organization) throws Exception {
		Connection conn = this.getConnection().getWytalDataSource();
		try {
			return getChildOrganization(organization,conn);
		}
		finally{
			this.getConnection().closeConnection(conn);
		}		
	}
	
	
	

	//TODO - How can we optimize this. Don't want one call on Address per Organization
	private Organization  populateOrganization(ResultSet rs, Connection conn) throws Exception{
		Organization org = new Organization();
		long addressid = 0L;
		org.setId(rs.getInt(1));
		int typeid  = rs.getInt(2);
		for(OrganizationType t : this.seedDataService.getOrganizationTypes()){
			if(t.getId()==typeid){
				org.setType(t);
				break;
			}
		}
		org.setName(rs.getString(3));
		org.setDescription(rs.getString(4));
		addressid = rs.getLong(5);
		org.setParentOrganization(rs.getInt(6));
		org.setCreatedDate(rs.getTimestamp(7));
		org.setLastUpdated(rs.getTimestamp(8));
		
		org.setAddress( this.addressDaoService.getAddress(addressid, conn));
		org.setContacts(this.orgContactService.get(org.getId(),conn));
		org.setChildren(this.getChildOrganizationID(org.getId(), conn));
		return org;
		
	}
	
	
	

	
	@Override
	public Organization getOrganization(long id,Connection conn) throws Exception{
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(this.getProperty("101"));
			ps.setLong(1, id);
			rs = ps.executeQuery();
			if(rs.next()){
				return this.populateOrganization(rs, conn);
			}
			return null;
		}
		catch(Exception ex){
			logger.error("Error querying the organization unit",ex);
			throw ex;
		}
		finally{
			this.getConnection().releaseResources(rs, ps, null);
		}
	}
	@Override
	public boolean deleteOrganization(long id,Connection conn) throws Exception{
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(this.getProperty("104"));
			ps.setLong(1,id);
			return (ps.executeUpdate()>0)?true:false;
		}
		finally{
			this.getConnection().closeStatement(ps);
		}
	}
	
	
	@Override
	public  void store(Organization org,Connection conn) throws Exception{
		//TODO - How can we avoid passing duplicate parameter
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			this.addressDaoService.storeAddress(org.getAddress(),conn);
			
			ps = conn.prepareStatement(this.getProperty("102"),Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, org.getType().getId());
			ps.setString(2,  org.getName());
			ps.setString(3, org.getDescription());
			if(org.getAddress().getAddressId() >0){
				ps.setLong(4, org.getAddress().getAddressId());
			}
			else {
				ps.setNull(4, java.sql.Types.INTEGER);
			}
			if(org.getParentOrganization() != null){
				ps.setInt(5, org.getParentOrganization());
			}
			else {
				ps.setNull(5, java.sql.Types.INTEGER);
			}
			ps.setInt(6, org.getType().getId());
			ps.setString(7,  org.getName());
			ps.setString(8, org.getDescription());
			if(org.getAddress().getAddressId() >0){
				ps.setLong(9, org.getAddress().getAddressId());
			}
			else {
				ps.setNull(9, java.sql.Types.INTEGER);
			}
			if(org.getParentOrganization() != null){
				ps.setInt(10, org.getParentOrganization());
			}
			else {
				ps.setNull(10, java.sql.Types.INTEGER);
			}
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			if(rs.next()){
				org.setId(rs.getInt(1));
			}
			else {
				//Update. Get the ID from db
				ps.close();
				rs.close();
				ps = conn.prepareStatement(this.getProperty("105"));
				ps.setString(1,org.getName());
				rs = ps.executeQuery();
				if(rs.next()){
					org.setId(rs.getInt(1));
				}
				
			}
			this.orgContactService.store(org.getId(),org.getContacts(),conn);
			
		}
		catch(Exception ex){
			logger.error("Error creating the organization unit",ex);
			throw ex;
		}
		finally{
			this.getConnection().releaseResources(rs, ps, null);
		}
	}

	
	
	private Organization getParentOrganization(int organization, Connection conn) throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(this.getProperty("106"));
			ps.setLong(1, organization);
			rs = ps.executeQuery();
			if(rs.next()){
				return this.populateOrganization(rs, conn);
			}
			return null;
		}
		catch(Exception ex){
			logger.error("Error querying the organization unit",ex);
			throw ex;
		}
		finally{
			this.getConnection().releaseResources(rs, ps, null);
		}
	}
	
	private List<Organization> getChildOrganization(int organization,Connection conn) throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			List<Organization> orgList = new ArrayList<>();
			ps = conn.prepareStatement(this.getProperty("107"));
			ps.setLong(1, organization);
			rs = ps.executeQuery();
			while(rs.next()){
				orgList.add(this.populateOrganization(rs, conn));
			}
			return orgList
					;
		}
		catch(Exception ex){
			logger.error("Error querying the organization unit",ex);
			throw ex;
		}
		finally{
			this.getConnection().releaseResources(rs, ps, null);
		}
	}
	
	
	private List<Integer> getChildOrganizationID(long orgParentid, Connection conn) throws Exception{
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			List<Integer> childList = new ArrayList<>();
			ps = conn.prepareStatement(this.getProperty("107"));
			ps.setLong(1, orgParentid);
			rs = ps.executeQuery();
			
			while(rs.next()){
				childList.add(rs.getInt(1));
			}
			return childList;
		}
		catch(Exception ex){
			logger.error("Error querying the organization unit",ex);
			throw ex;
		}
		finally{
			this.getConnection().releaseResources(rs, ps, null);
		}
	}
	
	
	public boolean isOrganizationValid(int orgId) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = this.getConnection().getWytalDataSource();
			ps = conn.prepareStatement(this.getProperty("108"));
			ps.setLong(1, orgId);
			rs = ps.executeQuery();
			
			if(rs.next()){
				return true;
			}

		}
		catch(Exception ex){
			logger.error("Error querying the organization unit",ex);
		}
		finally{
			this.getConnection().releaseResources(rs, ps, conn);
		}
		return false;
		
	}
	
	@Override
	public OrganizationSearchResponse search(OrganizationSearchRequest request) throws Exception{
		String name = (request.getName() == null && "".equals(request.getName().trim()))?null: request.getName();
		int typeid = (request.getType()==null || request.getType().getId()<=0)?0:request.getType().getId();
		String sql = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn =null;
		
		List<OrganizationItem> items = new ArrayList<>();
		int n=1;
		try {
			conn = this.getConnection().getWytalDataSource();
			
			if(typeid>0 && name ==null){
				//110
				sql = this.getProperty("110");
				ps = conn.prepareStatement(sql);
				ps.setInt(n++,typeid);
			}

			else if(typeid==0 && name != null){				
				sql = this.getProperty("111");
				ps = conn.prepareStatement(sql);
				ps.setString(n++,(name+"%"));
			}
			else if(typeid>0 && name != null){
				sql = this.getProperty("112");
				ps = conn.prepareStatement(sql);
				ps.setInt(n++,typeid);
				ps.setString(n++,name+"%");
			}
			else {
				sql = String.format(this.getProperty("109"), " ");
			}
		

			
			ps.setInt(n++, (request.getOffset() <0?0:request.getOffset()));
			ps.setInt(n++, (request.getLimit()+1));
			rs = ps.executeQuery();
			int c=0;
			while(rs.next()){
				items.add(c++,getItem(rs));
			}
			
			OrganizationSearchResponse sr =new OrganizationSearchResponse();
			if(items.size()>0 && items.size()>request.getLimit()){
				sr.setMore(true);
				
			}
			sr.setItems(items);
			sr.setLimit(request.getLimit());
			sr.setOffset(request.getOffset());
			return sr;
		}
		catch(Exception ex){
			logger.error("Exception querying the organization",ex);
			throw ex;
		}
		finally{
			this.getConnection().releaseResources(rs, ps,conn);
		}

	}
	
	private OrganizationItem getItem(ResultSet rs) throws Exception{
		OrganizationItem item = new OrganizationItem();
		item.setOrganizationId(rs.getInt(1));
		item.setOrganiztionName(rs.getString(2));
		item.setParentOrganizationId(rs.getInt(3));
		item.setParentOrganizationName(rs.getString(4));
		int typeid = rs.getInt(5);
		for(OrganizationType t : this.seedDataService.getOrganizationTypes()){
			if(typeid ==t.getId()){
				item.setType(t);
				break;
			}
		}
		return item;
	}
		

}
