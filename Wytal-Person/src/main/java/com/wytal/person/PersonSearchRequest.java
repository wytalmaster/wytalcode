package com.wytal.person;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PersonSearchRequest {
	private String firstName;
	private String lastName;
	private Date dob;
	private SortKeyword sortKey = SortKeyword.LASTNAME ;
	private SortDirection sortDir =SortDirection.ASC;
	private int offset;
	private int limit;
	private int organizationid;
	
	
	public enum SortKeyword {FIRSTNAME,LASTNAME}
	public enum SortDirection {ASC,DESC}
	public SortDirection getSortDir() {
		return sortDir;
	}
	public void setSortDir(SortDirection sortDir) {
		this.sortDir = sortDir;
	}
	public void setSortDir(String sortDir){
		for(SortDirection s : SortDirection.values()){
			if(s.name().equals(sortDir)){
				this.sortDir = s;
				break;
			}
		}
	}
	
	public SortKeyword getSortKey() {
		return sortKey;
	}
	public void setSortKey(SortKeyword sortKey) {
		this.sortKey = sortKey;
	}
	
	public void setSortKey(String sortKey){
		for(SortKeyword s : SortKeyword.values()){
			if(s.name().equals(sortKey)){
				this.sortKey = s;
				break;
			}
		}
	}
	
	
	public int getOrganizationid() {
		return organizationid;
	}
	public void setOrganizationid(int organizationid) {
		this.organizationid = organizationid;
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
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	
	public void setDob(String date){
		SimpleDateFormat df = new  SimpleDateFormat("yyyy-MM-dd");
		try {
			dob = df.parse(date);
		}
		catch(Exception ex){
			dob  = null;
		}
	}
	
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	
	

}
