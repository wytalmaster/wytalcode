package com.wytal.commons.seed.service;

import java.util.Collection;
import java.util.Map;

import com.wytal.commons.address.AddressType;
import com.wytal.commons.address.ProviderIdentiferType;
import com.wytal.commons.contact.ContactType;
import com.wytal.commons.org.OrganizationType;
import com.wytal.commons.relation.RelationType;
import com.wytal.commons.role.Role;
import com.wytal.commons.state.MedicalReportState;

public interface SeedDataService {
	
	public Collection<AddressType> getAddressType();
	public  Map<String,Role> getAllRolesByName() ;
	public  Map<Integer,Role> getAllRoles() ;
	public Role getRole(int id);
	public Role getRoleByName(String name);
	
	
	public Collection<RelationType> getRelationTypes();
	public Collection<ContactType> getContactTypes();
	
	public Collection<MedicalReportState> getMedicalReportTypes();
	public Collection<OrganizationType> getOrganizationTypes();
	public Collection<ProviderIdentiferType> getProviderIdentifierTypes();

}
