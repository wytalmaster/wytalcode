package com.wytal.organization;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.wytal.commons.address.Address;
import com.wytal.commons.contact.Contact;
import com.wytal.commons.org.OrganizationType;
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Organization implements Serializable {
	private static final long serialVersionUID = 100000013L;
	private int id;
	private String 	name;
	private OrganizationType type;
	private String description;
	private Address address;
	private Integer parentOrganization;
	private List<Integer> children;
	private List<Contact> contacts;
	
	private Date createdDate;
	private Date lastUpdated;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public Integer getParentOrganization() {
		return parentOrganization;
	}
	public void setParentOrganization(Integer parentOrganization) {
		this.parentOrganization = parentOrganization;
	}
	public List<Integer> getChildren() {
		return children;
	}
	public void setChildren(List<Integer> children) {
		this.children = children;
	}
	public List<Contact> getContacts() {
		return contacts;
	}
	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}
	
	public OrganizationType getType() {
		return type;
	}
	public void setType(OrganizationType type) {
		this.type = type;
	}
	
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getLastUpdated() {
		return lastUpdated;
	}
	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	
	
	
	
	
	
	

}
