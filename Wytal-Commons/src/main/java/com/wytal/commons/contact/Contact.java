package com.wytal.commons.contact;

import java.io.Serializable;

public class Contact implements Serializable{
	private static final long serialVersionUID = 100000003L;
	protected long id;
	protected ContactType contactType;
	protected String value;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public ContactType getContactType() {
		return contactType;
	}
	public void setContactType(ContactType contactType) {
		this.contactType = contactType;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	

}
