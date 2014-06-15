package com.wytal.organization.service;

import java.sql.Connection;
import java.util.List;

import com.wytal.organization.Organization;
import com.wytal.organization.OrganizationSearchRequest;
import com.wytal.organization.OrganizationSearchResponse;


public interface OrganizationDaoService {

	public void store(Organization org) throws Exception;
	public Organization getOrganization(long id) throws Exception;
	public boolean deleteOrganization(long id) throws Exception;
	
	public void store(Organization org,Connection conn) throws Exception;
	public Organization getOrganization(long id,Connection conn) throws Exception;
	public boolean deleteOrganization(long id,Connection conn) throws Exception;
	public Organization getParentOrganization(int organization) throws Exception;
	public List<Organization> getChildOrganization(int organization) throws Exception;
	public OrganizationSearchResponse search(OrganizationSearchRequest request) throws Exception;
	
	
	// ---- Internal API----
	public boolean isOrganizationValid(int orgId);

}