package com.wytal.commons.service.contact.impl;




	import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;

import com.wytal.commons.contact.Contact;
import com.wytal.commons.contact.ContactType;
import com.wytal.commons.seed.service.SeedDataService;
import com.wytal.commons.service.contact.ContactDaoService;
import com.wytal.logging.factory.WytalLoggingFactory;
import com.wytal.util.service.ServiceBase.ServiceResource;

	public class ContactDaoServiceImpl extends ServiceResource implements ContactDaoService{

		protected Logger logger = WytalLoggingFactory.getLogger(WytalLoggingFactory.ORG_LOGGER);
		private SeedDataService seedDataService;

		public void setSeedDataService(SeedDataService service){
			this.seedDataService= service;
		}
		
		@Override
		protected Logger getLogger() {
			return logger;
		}

		
		@Override
		public void store(long id,Collection<Contact> contacts, Connection conn) throws Exception{
			if(contacts==null){ return;}
			List<Contact> addContacts = new ArrayList<>(contacts.size());
			List<Contact> updateContacts = new ArrayList<>(contacts.size());
			for(Contact c : contacts){
				if(c.getId()<=0){
					//Update bucket
					addContacts.add(c);
				}
				else {
					//Add bucket
					updateContacts.add(c);
				}
			}
			addContacts(addContacts,id,conn);
			updateContacts(updateContacts,conn);
		
			
		}
		

		@Override
		public void store(long id,Collection<Contact> contacts) throws Exception{
			
			Connection conn = this.getConnection().getWytalDataSource();
			try {
				store(id,contacts,conn);
				conn.commit();
			}
			finally{
				conn.rollback();
				this.getConnection().closeConnection(conn);
			}		
		}
		
		

		@Override
		public Collection<Contact> get(long id) throws Exception {
			Connection conn = this.getConnection().getWytalDataSource();
			try {
				return get(id,conn);
			}
			finally{
				this.getConnection().closeConnection(conn);
			}		
		}
		

		@Override
		public Collection<Contact> get(long id, Connection conn) throws Exception {
			PreparedStatement ps = null;
			ResultSet rs= null;
			try {
				ps = conn.prepareStatement(this.getProperty("103"));
				ps.setLong(1, id);
				rs = ps.executeQuery();
				List<Contact> contacts = new ArrayList<>();
				Collection<ContactType> contactTypes = this.seedDataService.getContactTypes();
				while(rs.next()){
					Contact c = new Contact();
					c.setId(rs.getLong(1));
					int typeid = rs.getInt(2);
					for(ContactType ct : contactTypes){
						if(ct.getId()==typeid){
							c.setContactType(ct);
						}
					}
					c.setValue(rs.getString(3));
					contacts.add(c);
				}
				return contacts;
			}
			catch(Exception ex){
				logger.error("Error querying the organization contacts",ex);
				throw ex;
			}
			finally{
				this.getConnection().releaseResources(rs, ps, null);
			}
			
		}
		


		@Override
		public void delete(List<Long> contactids) throws Exception{
			Connection conn = this.getConnection().getWytalDataSource();
			try {
				delete(contactids,conn);
				conn.commit();
			}
			finally{
				conn.rollback();
				this.getConnection().closeConnection(conn);
			}		
		}
		



		@Override
		public void delete(List<Long> contactids, Connection conn) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				//TODO - Make sure this user is updating the data that he owns
				ps = conn.prepareStatement(this.getProperty("104"));
				for(Long i : contactids){
					ps.setLong(1,i.longValue());
					ps.addBatch();
				}
				ps.executeBatch();
			}
			catch(Exception ex){
				logger.error("Exception deleting contact",ex);
			}
			finally{
				this.getConnection().releaseResources(rs,ps,null);
			}
			
			
		}

		private void updateContacts(List<Contact> contacts,Connection conn) throws Exception{
			if(contacts.size()==0) { return;}
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				ps = conn.prepareStatement(this.getProperty("102"));
				for(Contact c : contacts){
					ps.setInt(1, c.getContactType().getId());
					ps.setString(2, c.getValue());
					ps.setInt(3, 0);
					ps.setLong(4,c.getId());
					ps.addBatch();
				}
				ps.executeBatch();
			}
			catch(Exception ex){
				logger.error("Exception adding contact",ex);
			}
			finally{
				this.getConnection().releaseResources(rs,ps,null);
			}
			
		}

		private void addContacts(List<Contact> contacts,long id,Connection conn)  throws Exception{
			if(contacts.size()==0) { return;}
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				ps = conn.prepareStatement(this.getProperty("101"),Statement.RETURN_GENERATED_KEYS);
				for(Contact c : contacts){
					ps.setLong(1,id); //Organization id
					ps.setInt(2, c.getContactType().getId());
					ps.setString(3, c.getValue());
					ps.setInt(4, 0);
					ps.executeUpdate();
					rs = ps.getGeneratedKeys();
					if(rs.next()){
						c.setId(rs.getLong(1));
					}
					rs.close();
				}
			}
			catch(Exception ex){
				logger.error("Exception adding contact",ex);
			}
			finally{
				this.getConnection().releaseResources(rs,ps,null);
			}
			
			
		}

	}
