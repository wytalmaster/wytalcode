package com.wytal.organization;

import com.wytal.commons.org.OrganizationType;

public class OrganizationItem {
	
	private int organizationId;
	private String organiztionName;
	private OrganizationType type;
	private int parentOrganizationId;
	private String parentOrganizationName;
	public int getOrganizationId() {
		return organizationId;
	}
	public void setOrganizationId(int organizationId) {
		this.organizationId = organizationId;
	}
	public String getOrganiztionName() {
		return organiztionName;
	}
	public void setOrganiztionName(String organiztionName) {
		this.organiztionName = organiztionName;
	}
	public OrganizationType getType() {
		return type;
	}
	public void setType(OrganizationType type) {
		this.type = type;
	}
	public int getParentOrganizationId() {
		return parentOrganizationId;
	}
	public void setParentOrganizationId(int parentOrganizationId) {
		this.parentOrganizationId = parentOrganizationId;
	}
	public String getParentOrganizationName() {
		return parentOrganizationName;
	}
	public void setParentOrganizationName(String parentOrganizationName) {
		this.parentOrganizationName = parentOrganizationName;
	}

	
	
}
