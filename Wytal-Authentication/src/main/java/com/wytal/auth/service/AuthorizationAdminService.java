package com.wytal.auth.service;

import com.wytal.commons.address.ProviderIdentiferType;
import com.wytal.commons.org.OrganizationType;
import com.wytal.commons.reponse.TypeCollection;
import com.wytal.commons.role.Role;
import com.wytal.commons.seed.service.Type;
import com.wytal.commons.state.MedicalReportState;

public interface AuthorizationAdminService {
	
	public TypeCollection<Role> getAllRoles() throws Exception;
	
	public Type getRole(int id) throws Exception;
	
	public TypeCollection<OrganizationType> getOrganizationType() throws Exception;
	public TypeCollection<MedicalReportState> getMedicalReportStates() throws Exception ;
	public TypeCollection<ProviderIdentiferType> getProviderIdentifierTypes() throws Exception ;

}
