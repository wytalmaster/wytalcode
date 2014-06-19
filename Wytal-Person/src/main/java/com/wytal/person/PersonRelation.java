package com.wytal.person;

import java.io.Serializable;

import com.wytal.commons.relation.RelationType;

public class PersonRelation implements Serializable {

	private static final long serialVersionUID = 100000020L;

	

	private RelationType relationType;
	private long relatedPersonId;
	public RelationType getRelationType() {
		return relationType;
	}
	public void setRelationType(RelationType relationType) {
		this.relationType = relationType;
	}
	public long getRelatedPersonId() {
		return relatedPersonId;
	}
	public void setRelatedPersonId(long personId) {
		this.relatedPersonId = personId;
	}
	
	
	
	
	
}
