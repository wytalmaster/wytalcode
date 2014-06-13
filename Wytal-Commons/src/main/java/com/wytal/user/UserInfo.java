package com.wytal.user;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.wytal.commons.address.Address;
import com.wytal.commons.address.AddressType;
import com.wytal.commons.role.Role;

public class UserInfo  extends User{
	
	private Map<AddressType,Address> addressMap;
	
	private Set<Role> roles;
	
	
	public void addAddressType(AddressType type, Address address){
		if(addressMap == null){
			addressMap = new HashMap<>();
		}
		addressMap.put(type, address);
	}
	
	public void removeAddress(AddressType type){
		if(addressMap != null){
			addressMap.remove(type);
		}
	}
	
	public Map<AddressType,Address> getAddress(){
		return Collections.unmodifiableMap(addressMap);
	}

	
	public void addRole(Role role){
		if(roles ==null){
			roles = new TreeSet<>();
		}
		roles.add(role);
	}
	
	public Set<Role> getRole(){
		return Collections.unmodifiableSet(roles);
	}
	
	public void removeRole(Role role){
		if(roles != null){
			roles.remove(role);
		}
	}
	

}
