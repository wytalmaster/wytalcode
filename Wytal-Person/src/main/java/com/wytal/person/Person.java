package com.wytal.person;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.wytal.commons.contact.Contact;

public class Person  implements Serializable{
	private static final long serialVersionUID = 100000002L;
	public static  enum Gender { MALE, FEMALE,UNKNOWN};
	
	private long 	id;
	private String 	firstName;
	private String 	lastName;
	private String 	middleName;
	private String 	otherName;
	private Date  	dob;
	private Gender 	gender;

	private String  nationalID;
	private String  secretKey;
	
	private Date	createdTime;
	private Date 	lastUpdated;
	private int 	organizationId;

	private Collection<PersonAddress> contactAddress;
	private Collection<Contact> contacts;
	private Collection<PersonRelation> relationShip;
	
	
	
	
	public Collection<PersonRelation> getRelationShip() {
		return relationShip;
	}
	public void setRelationShip(Collection<PersonRelation> relationShip) {
		this.relationShip = relationShip;
	}
	public String getNationalID() {
		return nationalID;
	}
	public void setNationalID(String nationalID) {
		this.nationalID = nationalID;
	}
	@JsonIgnore
	public String getSecretKey() {
		return secretKey;
	}
	@JsonIgnore
	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}
	public int getOrganizationId() {
		return organizationId;
	}
	public void setOrganizationId(int organizationId) {
		this.organizationId = organizationId;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getOtherName() {
		return otherName;
	}
	public void setOtherName(String otherName) {
		this.otherName = otherName;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
	
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public Date getLastUpdated() {
		return lastUpdated;
	}
	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	
	
	public Collection<PersonAddress> getContactAddress() {
		if(contactAddress==null){ return null;}
		return Collections.unmodifiableCollection(contactAddress);
	}
	
	public void setContactAddress(Collection<PersonAddress> address){
		 this.contactAddress = address;
	}
	
	
	public Collection<Contact> getContacts() {
		if(contacts==null){ return null;}
		 return Collections.unmodifiableCollection(contacts);
	}
	
	public void setContacts(Collection<Contact> contacts){
		 this.contacts = contacts;
	}
	public void addContacts(Contact contact) {
		if(this.contacts==null){
			this.contacts = new ArrayList<>();
		}
		this.contacts.add(contact);
	}
	
	@JsonIgnore
	public String getName(){
		StringBuilder sb = new StringBuilder();
		sb.append((firstName==null)?"":firstName).append(",");
		sb.append((middleName==null)?"":middleName).append(",");
		sb.append((lastName==null)?"":lastName);
		return sb.toString();
	}
	
	
	

}
