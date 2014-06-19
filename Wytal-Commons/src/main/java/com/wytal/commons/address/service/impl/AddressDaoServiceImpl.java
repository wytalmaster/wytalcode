package com.wytal.commons.address.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;

import com.wytal.commons.address.Address;
import com.wytal.commons.address.service.AddressDaoService;
import com.wytal.logging.factory.WytalLoggingFactory;
import com.wytal.util.service.ServiceBase.ServiceResource;

public class AddressDaoServiceImpl extends ServiceResource implements AddressDaoService{
	protected Logger logger = WytalLoggingFactory.getLogger(WytalLoggingFactory.COMMON_LOGGER);

	@Override
	protected Logger getLogger() {
		return logger;
	}

	/**
	 * 
	 * @param address
	 * @throws Exception
	 */
	@Override
	public void storeAddress(Address address) throws Exception{
		Connection	conn = null;
		try {
			conn = this.getConnection().getWytalDataSource();
			this.storeAddress(address, conn);
			conn.commit();
		}
		catch(Exception ex){
			this.getConnection().rollback(conn);
		}
		finally{
			this.getConnection().closeConnection(conn);
		}
	}
	
	
	/**
	 * 
	 * @param address
	 * @param conn
	 * @throws Exception
	 */
	@Override
	public void storeAddress(Address address, Connection conn) throws Exception{
		if(address.getAddressId() >0){
			this.updateAddress(address, conn);
		}
		else {
			this.addAddress(address, conn);
		}
	}
	
	
	private void addAddress(Address address,Connection conn) throws Exception{
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(this.getProperty("101"),Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, address.getAddressLine1());
			ps.setString(2, address.getAddressLine2());
			ps.setString(3,	address.getCity());
			ps.setString(4, address.getState());
			ps.setString(5, address.getCountry());
			ps.setString(6, address.getPostalCode());
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			if(rs.next()){
				address.setAddressId(rs.getLong(1));
			}
			
		}
		catch(Exception ex){
			logger.error("Unable to Insert Address" + address ,ex);
			throw ex;
		}
		finally{
			this.getConnection().closeResultSet(rs);
			this.getConnection().closeStatement(ps);
		}
		
	}
	
	private void updateAddress(Address address,Connection conn) throws Exception{
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(this.getProperty("102"));
			ps.setString(1, address.getAddressLine1());
			ps.setString(2, address.getAddressLine2());
			ps.setString(3,	address.getCity());
			ps.setString(4, address.getState());
			ps.setString(5, address.getCountry());
			ps.setString(6, address.getPostalCode());
			ps.setLong(7, address.getAddressId());
			ps.executeUpdate();
		}
		catch(Exception ex){
			logger.error("Unable to Update Address" + address ,ex);
			throw ex;
		}
		finally{
			this.getConnection().closeStatement(ps);
		}
	}
	
	@Override
	public List<Address> getAddress(long[] ids) throws Exception {
		Connection	conn = null;
		try {
			conn = this.getConnection().getWytalDataSource();
			return getAddress(ids,conn);
		}
		finally{
			this.getConnection().closeConnection(conn);
		}
	}
	@Override
	public List<Address> getAddress(long[] ids,Connection conn) throws Exception {
		Statement st = null;
		ResultSet rs = null;
		try {
			if(ids ==null){
				return null;
			}
			StringBuilder sb = new StringBuilder();
			for(long id : ids){
				sb.append(id).append(",");
			}
			sb.deleteCharAt(sb.length()-1);
			String s = this.getProperty("103");
			s = String.format(s, sb.toString());
			st = conn.createStatement();
			rs = st.executeQuery(s);
			List<Address> addressList = new ArrayList<>();
			while(rs.next()){
				addressList.add(this.getAddress(rs));
			}
			return addressList;
		}
		finally{
			this.getConnection().releaseResources(rs, st,null);
		}
	}
	
	
	@Override
	public Address getAddress(long id) throws Exception {
		Connection	conn = null;
		try {
			conn = this.getConnection().getWytalDataSource();
			return getAddress(id,conn);
		}
		finally{
			this.getConnection().closeConnection(conn);
		}
	}
	
	
	@Override
	public Address getAddress(long id,Connection conn) throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String s = this.getProperty("104");
			ps = conn.prepareStatement(s);
			ps.setLong(1, id);
			rs = ps.executeQuery();
			if(rs.next()){
				return this.getAddress(rs);
			}
			return null;
		}
		finally{
			this.getConnection().releaseResources(rs, ps, null);
		}
	}
	
	
	@Override
	public Map<Long, Address> getAllAddress(long personid,Connection conn) throws Exception{
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String s = this.getProperty("105");
			ps = conn.prepareStatement(s);
			ps.setLong(1, personid);
			rs = ps.executeQuery();
			Map<Long,Address> addressMap = new HashMap<>();
			while(rs.next()){
				Address a =  this.getAddress(rs);
				addressMap.put(a.getAddressId(),a);
			}
			return addressMap;
		}
		catch(Exception ex){
			logger.error("Error fetching address",ex);
			throw ex;
		}
		finally{
			this.getConnection().releaseResources(rs, ps, null);
		}
	}
	
	
	private Address getAddress(ResultSet rs) throws Exception{
		Address address = new Address();
		address.setAddressId(rs.getLong(1));
		address.setAddressLine1(rs.getString(2));
		address.setAddressLine2(rs.getString(3));
		address.setCity(rs.getString(4));
		address.setState(rs.getString(5));
		address.setCountry(rs.getString(6));
		address.setPostalCode(rs.getString(7));
		address.setCreated(rs.getTimestamp(8));
		address.setLastUpdated(rs.getTimestamp(9));
		
		return address;
		
	}
}
