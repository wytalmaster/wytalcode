package com.wytal.person.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;

import com.wytal.commons.relation.RelationType;
import com.wytal.commons.seed.service.SeedDataService;
import com.wytal.logging.factory.WytalLoggingFactory;
import com.wytal.person.PersonRelation;
import com.wytal.person.service.PersonRelationDaoService;
import com.wytal.util.service.ServiceBase.ServiceResource;

public class PersonRelationDaoServiceImpl extends ServiceResource implements PersonRelationDaoService {

	protected Logger logger = WytalLoggingFactory.getLogger(WytalLoggingFactory.PERSON_LOGGER);
	
	@Override
	protected Logger getLogger() {
		return logger;
	}
	
	
	private SeedDataService seedDataService;
	public void setSeedDataService(SeedDataService service){
		this.seedDataService = service;
	}
	
	
	@Override
	public void store(long personid,Collection<PersonRelation> relations) throws Exception{
		Connection conn = null;
		try {
			conn = this.getConnection().getWytalDataSource();
			this.store(personid, relations,conn);
			conn.commit();
		}
		catch(Exception ex){
			conn.rollback();
			throw ex;
		}
		finally{
			this.getConnection().closeConnection(conn);
		}
	}
	
	@Override
	public Collection<PersonRelation> get(long personid) throws Exception{
		Connection conn = null;
		try {
			conn = this.getConnection().getWytalDataSource();
			return this.get(personid,conn);
		}
		catch(Exception ex){
			throw ex;
		}
		finally{
			this.getConnection().closeConnection(conn);
		}
	}
	
	@Override
	public void delete(long personid, List<Long> relationPersonId) throws Exception{
		Connection conn = null;
		try {
			conn = this.getConnection().getWytalDataSource();
			this.delete(personid,relationPersonId,conn);
			conn.commit();
		}
		catch(Exception ex){
			conn.rollback();
			throw ex;
		}
		finally{
			this.getConnection().closeConnection(conn);
		}
	}
	
	@Override
	public void store(long personid,Collection<PersonRelation> relations,Connection conn) throws Exception{
		PreparedStatement ps = null;
		try{
			if(relations ==null|| relations.size()==0){
				return ;
			}
			ps = conn.prepareStatement(this.getProperty("101"));
			for(PersonRelation pr :relations){
				ps.clearParameters();
			    ps.setLong(1, personid);
			    ps.setInt(2, pr.getRelationType().getId());
			    ps.setLong(3,pr.getRelatedPersonId());
			    ps.setInt(4, pr.getRelationType().getId());
			    ps.addBatch();
			}
			ps.executeBatch();
		}
		catch(Exception ex){
			logger.error("Error in storing related Person",ex);
			throw ex;
		}
		finally{
			this.getConnection().closeStatement(ps);
		}
		
	}
	
	@Override
	public List<PersonRelation> get(long personid,Connection conn) throws Exception{
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<PersonRelation> relationList = new ArrayList<>();
		try {
			System.out.println(this.getProperty("102"));
			ps= conn.prepareStatement(this.getProperty("102"));
			ps.setLong(1, personid);
			rs = ps.executeQuery();
			System.out.println(personid);
			while(rs.next()){
				PersonRelation pr = new PersonRelation();
				pr.setRelatedPersonId(rs.getLong(2));
				int relationType = rs.getInt(1);
				System.out.println("relationType" + relationType);
				
				for(RelationType r : this.seedDataService.getRelationTypes()){
					if(r.getId()==relationType){
						System.out.println(r.getName());
						pr.setRelationType(r);
						break;
					}
				}
				relationList.add(pr);
				
			}
			return relationList;
		}
		catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}
		finally{
			this.getConnection().releaseResources(rs,ps,null);
		}
		
		
	}
	
	
	@Override
	public void delete(long personid, List<Long> relationPersonId,Connection conn) throws Exception{
		 PreparedStatement ps = null;
		 try {
			 ps = conn.prepareStatement(this.getProperty("103"));
			 for(Long r : relationPersonId){
				 ps.clearParameters();
				 ps.setLong(1, personid);
				 ps.setLong(2, r.longValue());
				 ps.addBatch();
			 }
			 ps.executeBatch();
		 }
		 catch(Exception ex){
			 logger.error("Error deleting person relation ",ex);
			 throw ex;
		 }
		 finally{
				this.getConnection().closeStatement(ps);
		 }
	}
	
	
}
