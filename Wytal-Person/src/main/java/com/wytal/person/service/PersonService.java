package com.wytal.person.service;

import com.wytal.person.Person;
import com.wytal.person.PersonSearchResponse;
import com.wytal.util.exception.WytalException;

public interface PersonService {
	
	
	public void add(Person person) throws WytalException;
	
	public void update(Person person) throws WytalException;
	
	public Person getPerson(long person) throws WytalException;
	
	public void deletePerson(long person) throws WytalException;
	
	public PersonSearchResponse search( String lastName,
										String firstName,
										String dob,
										int organizationid,
										String sortKey,
										String sortType,
										int offset,
										int limit) throws WytalException;
					

}
