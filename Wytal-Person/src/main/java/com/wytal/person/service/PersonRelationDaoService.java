package com.wytal.person.service;

import java.sql.Connection;
import java.util.Collection;
import java.util.List;

import com.wytal.person.PersonRelation;

public interface PersonRelationDaoService {
	public void store(long personid, Collection<PersonRelation> relations) throws Exception;
	public Collection<PersonRelation> get(long personid) throws Exception;
	public void delete(long personid, List<Long> relationId) throws Exception;
	
	public void store(long personid, Collection<PersonRelation> relations,Connection conn) throws Exception;
	public Collection<PersonRelation> get(long personid,Connection conn) throws Exception;
	public void delete(long personid, List<Long> relationPersonId,Connection conn) throws Exception;

}
