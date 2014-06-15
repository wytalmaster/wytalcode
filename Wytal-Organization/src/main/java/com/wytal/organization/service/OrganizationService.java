package com.wytal.organization.service;

import java.util.List;

import com.wytal.organization.Organization;
import com.wytal.organization.OrganizationSearchResponse;
import com.wytal.util.exception.WytalException;

public interface OrganizationService {
	
	public void add(Organization org) throws WytalException;
	
	public void uddate(Organization org) throws WytalException;
	
	public Organization getOrganization(int organization) throws WytalException;
	
	public Organization getParentOrganization(int organization) throws WytalException;
	
	public List<Organization> getChildOrganization(int organization) throws WytalException;
	
	public void deleteOrganization(int organization) throws WytalException;
	
	
	public OrganizationSearchResponse search(int typeid, String name,int offset,int limit) throws WytalException;
		

	
}
