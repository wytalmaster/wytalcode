package com.wytal.commons.address.service;


import java.util.List;

import com.wytal.commons.address.Address;

public interface AddressService {
	public void storeAddress(Address address) throws Exception;
	public List<Address> getAddress(long[] ids) throws Exception;
	public Address getAddress(long id) throws Exception;

}
