package com.wytal.person.service;

import java.sql.Connection;

import com.wytal.person.Person;
import com.wytal.person.PersonSearchRequest;
import com.wytal.person.PersonSearchResponse;
import com.wytal.util.exception.WytalException;

public interface PersonDaoService {
	public void add(Person person) throws WytalException;
	public void add(Person person,Connection conn) throws WytalException;
	
	public void update(Person person) throws WytalException;
	public void update(Person person,Connection conn) throws WytalException;
	
	public Person getPerson(long person) throws WytalException;
	public Person getPerson(long person,Connection conn) throws WytalException;
	
	public void deletePerson(long person) throws WytalException;
	public void deletePerson(long person,Connection conn) throws WytalException;
	
	public PersonSearchResponse search(PersonSearchRequest request) throws WytalException;

}
