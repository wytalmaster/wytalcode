package com.wytal.commons.role;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.wytal.commons.seed.service.Type;

public class Role  extends Type {

	public Role(){
		super();
		this.api = new HashSet<>();
	}
	private static final long serialVersionUID = 100000005L;
	
	private Set<String> api;

	public Set<String> getApi() {
		return Collections.unmodifiableSet(api);
	}

	public void setApi(String api) {
		this.api.add(api);
	}

}
