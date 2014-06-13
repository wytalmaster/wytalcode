package com.wytal.commons.seed.service.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.wytal.commons.address.AddressType;
import com.wytal.commons.address.ProviderIdentiferType;
import com.wytal.commons.contact.ContactType;
import com.wytal.commons.org.OrganizationType;
import com.wytal.commons.relation.RelationType;
import com.wytal.commons.role.Role;
import com.wytal.commons.seed.service.SeedDataDaoService;
import com.wytal.commons.seed.service.SeedDataService;
import com.wytal.commons.state.MedicalReportState;

public class SeedDataServiceImpl implements SeedDataService {
	
	public void init() throws Exception{
		this.addressTypes =Collections.unmodifiableCollection(this.daoService.getAddressType());
		this.roleMapById =Collections.unmodifiableMap(this.daoService.getAllRoles());
		roleMapByName =new HashMap<>(this.roleMapById.size());
		for(Role r: roleMapById.values()){
			roleMapByName.put(r.getName(),r);
		}
		roleMapByName = Collections.unmodifiableMap(roleMapByName);
		
		this.relationTypes = Collections.unmodifiableCollection(this.daoService.getRelationTypes());
		this.contactTypes = Collections.unmodifiableCollection(this.daoService.getContactTypes());
		
		this.medicalReportStates = Collections.unmodifiableCollection(this.daoService.getMedicalReportTypes());
		this.organizationTypes = Collections.unmodifiableCollection(this.daoService.getOrganizationTypes());
		this.identifierTypes = Collections.unmodifiableCollection(this.daoService.getProviderIdentifierTypes());
		
		
		
	}
	private Collection<AddressType> addressTypes;
	private Map<String,Role> roleMapByName;
	private Map<Integer,Role> roleMapById;
	
	private Collection<RelationType> relationTypes;
	private Collection<ContactType> contactTypes;
	private Collection<MedicalReportState> medicalReportStates;
	private Collection<OrganizationType> organizationTypes;
	private Collection<ProviderIdentiferType> identifierTypes;
	
	
	private SeedDataDaoService daoService;
	
	
	
	public void setSeedDataDaoService(SeedDataDaoService service){
		 this.daoService = service;
	}
	@Override
	public Collection<AddressType> getAddressType() {
		return this.addressTypes;
	}

	@Override
	public Map<Integer,Role> getAllRoles() {
		return roleMapById;
	}

	@Override
	public  Map<String,Role> getAllRolesByName() {
		return roleMapByName;
	}
	
	
	public Role getRole(int id){
		return this.roleMapById.get(id);
	}
	
	public Role getRoleByName(String name){
		return this.roleMapByName.get(name);
	}
	
	// RelationType
	
	public Collection<RelationType> getRelationTypes(){
		return this.relationTypes;
	}
	
	//ContactType
	
	public Collection<ContactType> getContactTypes(){
		return this.contactTypes;
	}
	
	public Collection<MedicalReportState> getMedicalReportTypes(){
		return this.medicalReportStates;
	}
	public Collection<OrganizationType> getOrganizationTypes(){
		return this.organizationTypes;
	}
	public Collection<ProviderIdentiferType> getProviderIdentifierTypes(){
		return this.identifierTypes;
	}
}
