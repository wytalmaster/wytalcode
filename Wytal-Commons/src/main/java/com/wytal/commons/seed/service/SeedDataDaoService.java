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

public interface SeedDataDaoService {
		
		public Collection<AddressType> getAddressType() throws Exception;
		public Map<Integer,Role> getAllRoles() throws Exception;
		public Collection<RelationType> getRelationTypes() throws Exception;
		public Collection<ContactType> getContactTypes() throws Exception;
		public Collection<MedicalReportState> getMedicalReportTypes() throws Exception;
		public Collection<OrganizationType> getOrganizationTypes() throws Exception;
		public Collection<ProviderIdentiferType> getProviderIdentifierTypes() throws Exception;
}
