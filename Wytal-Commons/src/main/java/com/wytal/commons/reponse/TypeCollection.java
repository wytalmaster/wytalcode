package com.wytal.commons.reponse;

import java.util.Collection;
import java.util.Collections;

import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlRootElement;

import com.wytal.commons.seed.service.Type;

@XmlRootElement
public class TypeCollection <T extends Type> {
	@XmlList
	private Collection<T>  data;

	public Collection<T> getData() {
		return Collections.unmodifiableCollection(data);
	}

	public void setData( Collection<T> data) {
		this.data = data;
	}
	
	
	
	

}
