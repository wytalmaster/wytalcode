package com.wytal.organization.service;

import java.sql.Connection;
import java.util.List;

import com.wytal.organization.Organization;

public interface OrganizationContactDaoService {

	public  void store(Organization org, Connection conn) throws Exception;
	public  void store(Organization org) throws Exception;
	public  void populateOrganizationContacts(Organization org) throws Exception;
	public  void populateOrganizationContacts(Organization org, Connection conn) throws Exception;
	public  void deleteContacts(List<Long> contactids) throws Exception;
	public  void delteContacts(List<Long> contactids, Connection conn);


}
