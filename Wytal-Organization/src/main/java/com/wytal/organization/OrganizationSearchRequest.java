package com.wytal.organization;

import com.wytal.commons.org.OrganizationType;

public class OrganizationSearchRequest {
	
	private OrganizationType type;
	private String name;
	private int offset;
	private int limit;
	public OrganizationType getType() {
		return type;
	}
	public void setType(OrganizationType type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
