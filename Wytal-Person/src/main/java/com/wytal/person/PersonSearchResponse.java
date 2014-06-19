package com.wytal.person;

import java.util.List;

public class PersonSearchResponse {
	private String sortKey;
	private String sortDir;
private int offset;
	private int limit;
	private boolean more;
	private List<PersonItem> items;
	public String getSortKey() {
		return sortKey;
	}
	public void setSortKey(String sortKey) {
		this.sortKey = sortKey;
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
	public List<PersonItem> getItems() {
		return items;
	}
	public void setItems(List<PersonItem> items) {
		this.items = items;
	}
	public String getSortDir() {
		return sortDir;
	}
	public void setSortDir(String sortDir) {
		this.sortDir = sortDir;
	}
	
	
	

}
