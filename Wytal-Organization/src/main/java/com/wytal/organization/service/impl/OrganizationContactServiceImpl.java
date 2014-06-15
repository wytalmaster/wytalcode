package com.wytal.organization.service.impl;

import java.util.List;

import com.wytal.organization.Organization;
import com.wytal.organization.service.OrganizationContactDaoService;
import com.wytal.organization.service.OrganizationContactService;

public class OrganizationContactServiceImpl implements OrganizationContactService {
	
	public void init(){}
	private OrganizationContactDaoService service;
	public void setOrganizationContactService(OrganizationContactDaoService service){
		this.service = service;
	}
	@Override
	public void store(Organization org) throws Exception {
		this.service.store(org);

	}

	@Override
	public void populateOrganizationContacts(Organization org) throws Exception {
		this.service.populateOrganizationContacts(org);

	}

	@Override
	public void deleteContacts(List<Long> contactids) throws Exception {
		this.service.deleteContacts(contactids);

	}

}
