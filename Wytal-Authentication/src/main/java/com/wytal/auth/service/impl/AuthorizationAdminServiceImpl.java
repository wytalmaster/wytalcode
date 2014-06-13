package com.wytal.auth.service.impl;

import java.util.Collection;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import com.wytal.auth.service.AuthorizationAdminService;
import com.wytal.auth.service.dao.AuthorizationAdminDaoService;
import com.wytal.commons.address.AddressType;
import com.wytal.commons.address.ProviderIdentiferType;
import com.wytal.commons.contact.ContactType;
import com.wytal.commons.org.OrganizationType;
import com.wytal.commons.relation.RelationType;
import com.wytal.commons.reponse.TypeCollection;
import com.wytal.commons.role.Role;
import com.wytal.commons.seed.service.SeedDataService;
import com.wytal.commons.seed.service.Type;
import com.wytal.commons.state.MedicalReportState;

public class AuthorizationAdminServiceImpl implements AuthorizationAdminService{
	//private AuthorizationAdminDaoService service;
	private SeedDataService seedDataService;
	
	public void setAuthorizationDaoService(AuthorizationAdminDaoService service){
		//this.service = service;
	}
	
	public void setSeedDataService(SeedDataService service){
		this.seedDataService = service;
	}
	
	
	/* ----------------------------------
	 * --------------Roles----------------
	 * -----------------------------------
	 */
	@GET
	@Path("/Role")
	public TypeCollection<Role> getAllRoles() throws Exception{
		Map<Integer, Role> role = this.seedDataService.getAllRoles();
		TypeCollection<Role> coll = new TypeCollection<>();
		coll.setData(role.values());
		return coll;
	}
	
	@GET
	@Path("/Role/{id}")
	public Type getRole(int id) throws Exception{
		Map<Integer, Role> role = this.seedDataService.getAllRoles();
		return role.get(id);
	}
	
	
	
	
	/* ----------------------------------
	 * -----------Address Type----------
	 * -----------------------------------
	 */
	@GET
	@Path("/AddressType")
	public TypeCollection<AddressType> getAddressType() throws Exception {
		Collection<AddressType> addressTypes = this.seedDataService.getAddressType();
		TypeCollection<AddressType> coll = new TypeCollection<>();
		coll.setData(addressTypes);
		return coll;
	}
	
	

	/* ----------------------------------
	 * -----------Relation Type----------
	 * -----------------------------------
	 */
	@GET
	@Path("/RelationType")
	public TypeCollection<RelationType> getRelationType() throws Exception {
		Collection<RelationType> relationTypes = this.seedDataService.getRelationTypes();
		TypeCollection<RelationType> coll = new TypeCollection<>();
		coll.setData(relationTypes);
		return coll;
	}
	
	
	/* ----------------------------------
	 * -----------Contact Type----------
	 * -----------------------------------
	 */
	@GET
	@Path("/ContactType")
	public TypeCollection<ContactType> getContactType() throws Exception {
		Collection<ContactType> contactTypes = this.seedDataService.getContactTypes();
		TypeCollection<ContactType> coll = new TypeCollection<>();
		coll.setData(contactTypes);
		return coll;
	}
	

	/* ----------------------------------
	 * --------Medical Report State------
	 * -----------------------------------
	 */
	@GET
	@Path("/MedicalReportStates")
	public TypeCollection<MedicalReportState> getMedicalReportStates() throws Exception {
		Collection<MedicalReportState> mrTypes = this.seedDataService.getMedicalReportTypes();
		TypeCollection<MedicalReportState> coll = new TypeCollection<>();
		coll.setData(mrTypes);
		return coll;
	}
	
	
	/* ----------------------------------
	 * --------Organization Types------
	 * -----------------------------------
	 */
	@GET
	@Path("/OrganizationType")
	public TypeCollection<OrganizationType> getOrganizationType() throws Exception {
		Collection<OrganizationType> oTypes = this.seedDataService.getOrganizationTypes();
		TypeCollection<OrganizationType> coll = new TypeCollection<>();
		coll.setData(oTypes);
		return coll;
	}
	
	/* ----------------------------------
	 * --------Organization Types------
	 * -----------------------------------
	 */
	@GET
	@Path("/ProviderIdentifierTypes")
	public TypeCollection<ProviderIdentiferType> getProviderIdentifierTypes() throws Exception  {
		Collection<ProviderIdentiferType> iTypes = this.seedDataService.getProviderIdentifierTypes();
		TypeCollection<ProviderIdentiferType> coll = new TypeCollection<>();
		coll.setData(iTypes);
		return coll;
	}
}
