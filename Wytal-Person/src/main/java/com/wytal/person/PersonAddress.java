package com.wytal.person;

import java.io.Serializable;

import com.wytal.commons.address.Address;
import com.wytal.commons.address.AddressType;

public class PersonAddress implements Serializable{
	private static final long serialVersionUID = 100000021L;
	private long id;
	
	private AddressType type;
	private Address address;
	private boolean primary;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public boolean isPrimary() {
		return primary;
	}
	public void setPrimary(boolean primary) {
		this.primary = primary;
	}

	public AddressType getType() {
		return type;
	}
	public void setType(AddressType type) {
		this.type = type;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	
	

}
