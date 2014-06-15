package com.wytal.organization.service;

import java.util.List;

import com.wytal.organization.Organization;

public interface OrganizationContactService {
	public  void store(Organization org) throws Exception;
	public  void populateOrganizationContacts(Organization org) throws Exception;
	public  void deleteContacts(List<Long> contactids) throws Exception;

}
