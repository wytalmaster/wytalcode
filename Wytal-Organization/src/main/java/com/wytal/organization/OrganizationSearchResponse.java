package com.wytal.organization;

import java.util.List;

public class OrganizationSearchResponse {
	
	private List<OrganizationItem> items;
	private int offset;
	private int limit;
	private boolean more=false;
	public List<OrganizationItem> getItems() {
		return items;
	}
	public void setItems(List<OrganizationItem> items) {
		this.items = items;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public boolean isMore() {
		return more;
	}
	public void setMore(boolean more) {
		this.more = more;
	}
	
	

}
