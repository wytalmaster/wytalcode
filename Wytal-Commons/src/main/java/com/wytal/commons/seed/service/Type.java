package com.wytal.commons.seed.service;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlType;
@XmlType(name="", propOrder = {"id", "name", "description"})
public abstract class Type implements Serializable {
	
	private static final long serialVersionUID = 100000003L;
	private int id;
	private String name;
	private String description;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	

}
