package com.wytal.person.service;

import java.util.Collection;
import java.util.List;

import com.wytal.person.PersonRelation;
import com.wytal.util.exception.WytalException;

public interface PersonRelationService {
	public void store(long personid,Collection<PersonRelation> relations) throws WytalException;
	public Collection<PersonRelation> get(long personid) throws WytalException;
	public void delete(long personid, List<Long> relationId) throws WytalException;

}
