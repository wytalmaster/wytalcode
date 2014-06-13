package com.wytal.commons.seed.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;

import com.wytal.commons.address.AddressType;
import com.wytal.commons.address.ProviderIdentiferType;
import com.wytal.commons.contact.ContactType;
import com.wytal.commons.org.OrganizationType;
import com.wytal.commons.relation.RelationType;
import com.wytal.commons.role.Role;
import com.wytal.commons.seed.service.SeedDataDaoService;
import com.wytal.commons.seed.service.Type;
import com.wytal.commons.state.MedicalReportState;
import com.wytal.logging.factory.WytalLoggingFactory;
import com.wytal.util.service.ServiceBase.ServiceResource;

public class SeedDataDaoServiceImpl extends ServiceResource  implements SeedDataDaoService {

	
	protected Logger logger = WytalLoggingFactory.getLogger(WytalLoggingFactory.COMMON_LOGGER);
	
	
	@Override
	public Collection<AddressType> getAddressType() throws Exception {
//		Connection conn = null;
//		PreparedStatement ps = null;
//		ResultSet rs = null;
//		try {
//			Collection<AddressType> addressTypeCol = new LinkedList<>();
//			conn = this.getConnection().getWytalDataSource();
//			ps = conn.prepareStatement(prop.getProperty("201"));
//			rs = ps.executeQuery();
//			
//			while(rs.next()){
//				addressTypeCol.add((AddressType) getType(rs, AddressType.class));
//			}
//			return addressTypeCol;
//		}
//		catch(Exception ex){
//			logger.error(ex.getMessage(),ex);
//			throw ex;
//		}
//		finally{
//			this.getConnection().releaseResources(rs, ps, conn);
//		}
		return this.getType("201",AddressType.class);
	}
	

	@SuppressWarnings("unchecked")
	private <T extends Type> Collection<T> getType(String queryid, Class<? extends Type> c) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Collection<T> coll = new LinkedList<>();
			conn = this.getConnection().getWytalDataSource();
			ps = conn.prepareStatement(prop.getProperty(queryid));
			rs = ps.executeQuery();
			
			while(rs.next()){
				coll.add((T)getType(rs, c));
			}
			return coll;
		}
		catch(Exception ex){
			logger.error(ex.getMessage(),ex);
			throw ex;
		}
		finally{
			this.getConnection().releaseResources(rs, ps, conn);
		}
	}
	
	
	
	
	private Type getType(ResultSet rs,Class<? extends Type> t) throws SQLException, IllegalAccessException, InstantiationException{
		Type type = t.newInstance();
		type.setId(rs.getInt(1));
		type.setName(rs.getString(2));
		type.setDescription(rs.getString(3));
		return type;
		
	}
	

	@Override
	public Map<Integer,Role> getAllRoles() throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Map<Integer,Role> roleMap = new TreeMap<>();
			conn = this.getConnection().getWytalDataSource();
			String query = prop.getProperty("101");
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()){
				Role t = (Role)getType(rs,Role.class);
				roleMap.put(t.getId(),t);
			}
			rs.close();
			ps.close();
			ps = conn.prepareStatement(prop.getProperty("102"));
			rs = ps.executeQuery();
			while(rs.next()){
				int roleid = rs.getInt(1);
				String api = rs.getString(2);
				Role r = roleMap.get(roleid);
				if(r != null){
					r.setApi(api);
				}
			}
			
			return roleMap;
		}
		catch(Exception ex){
			ex.printStackTrace();
			logger.error(ex.getMessage(),ex);
			throw ex;
		}
		finally{
			this.getConnection().releaseResources(rs, ps, conn);
		}
	}

	
	
	@Override
	public Collection<RelationType> getRelationTypes() throws Exception{
		return this.getType("401",RelationType.class);
	}
	
	@Override
	public Collection<ContactType> getContactTypes() throws Exception{
		return this.getType("301",ContactType.class);
	}
	
	@Override
	public Collection<MedicalReportState> getMedicalReportTypes() throws Exception{
		return this.getType("501",MedicalReportState.class);
	}
	
	
	@Override
	public Collection<OrganizationType> getOrganizationTypes() throws Exception{
		return this.getType("601",OrganizationType.class);
	}
	
	
	@Override
	public Collection<ProviderIdentiferType> getProviderIdentifierTypes() throws Exception{
		return this.getType("701",ProviderIdentiferType.class);
	}
	
	
	
	@Override
	protected Logger getLogger() {
		return logger;
	}

}
