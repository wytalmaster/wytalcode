package com.wytal.commons.address;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wytal.commons.seed.service.Type;

public class Person  implements Serializable{
	private static final long serialVersionUID = 100000002L;
	public static  enum Gender { MALE, FEMALE,UNKNOWN};
	
	private long id;
	private String firstName;
	private String lastName;
	private String middleName;
	private String otherName;
	private Date  dob;
	private Gender gender;
	
	private Map<Type,Address> addressMap;
	private List<PersonContact> contacts;
	
	
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
	
	public Map<Type, Address> getAddressMap() {
		return Collections.unmodifiableMap(addressMap);
	}
	public void addAddressMap(Type type , Address address) {
		if (this.addressMap == null){
			this.addressMap = new HashMap<>();
		}
		this.addressMap.put(type, address);
	}
	
	public void removeAddresMap(Type type ){
		if (this.addressMap != null){
			this.addressMap.remove(type);
		}
	}
	
	public List<PersonContact> getContacts() {
		 return Collections.unmodifiableList(contacts);
	}
	public void setContacts(PersonContact contact) {
		if(this.contacts==null){
			this.contacts = new ArrayList<>();
		}
		this.contacts.add(this.contacts.size(),contact);
	}
	
	public String getName(){
		StringBuilder sb = new StringBuilder();
		sb.append((firstName==null)?"":firstName).append(",");
		sb.append((middleName==null)?"":middleName).append(",");
		sb.append((lastName==null)?"":lastName);
		return sb.toString();
	}
	
	
	

}
