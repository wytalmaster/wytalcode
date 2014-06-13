package com.wytal.commons.address;

import java.io.Serializable;

import com.wytal.commons.seed.service.Type;

public class PersonContact implements Serializable{
	private static final long serialVersionUID = 100000003L;
	private int id;
	private Type contactType;
	private String value;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public Type getContactType() {
		return contactType;
	}
	public void setContactType(Type contactType) {
		this.contactType = contactType;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	

}
