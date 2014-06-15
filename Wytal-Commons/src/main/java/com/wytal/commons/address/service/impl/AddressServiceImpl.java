package com.wytal.commons.address.service.impl;

import java.util.List;

import com.wytal.commons.address.Address;
import com.wytal.commons.address.service.AddressDaoService;
import com.wytal.commons.address.service.AddressService;

public class AddressServiceImpl implements AddressService {
	
	
	public void init(){
		
	}
	private AddressDaoService service;
	public void setAddresDaoService(AddressDaoService service){
		this.service = service;
	}
	@Override
	public void storeAddress(Address address) throws Exception {
		this.service.storeAddress(address);
		
	}
	@Override
	public List<Address> getAddress(long[] ids) throws Exception {
		return this.service.getAddress(ids);
	}
	@Override
	public Address getAddress(long id) throws Exception {
		return this.service.getAddress(id);
	}

}
