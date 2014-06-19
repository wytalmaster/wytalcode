package com.wytal.commons.address.service;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.wytal.commons.address.Address;

public interface AddressDaoService {
	
	public void storeAddress(Address address) throws Exception;
	public void storeAddress(Address address, Connection conn) throws Exception;
	public List<Address> getAddress(long[] ids) throws Exception;
	public List<Address> getAddress(long[] ids,Connection conn) throws Exception;
	public Address getAddress(long id) throws Exception;
	public Address getAddress(long id,Connection conn) throws Exception;
	public Map<Long, Address> getAllAddress(long personid,Connection conn) throws Exception;

}