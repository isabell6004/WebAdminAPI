package net.fashiongo.webadmin.common;

import java.util.List;

import net.fashiongo.webadmin.common.SingleValueResult;

public class PagedResult<T> {
	private SingleValueResult total;
	
	public SingleValueResult getTotal() {
		return total;
	}

	public void setTotal(SingleValueResult total) {
		this.total = total;
	}

	private Integer pageNumber;
	
	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	private List<T> records;

	public List<T> getRecords() {
		return records;
	}

	public void setRecords(List<T> records) {
		this.records = records;
	}
	
	private String listKey;
	public String getListKey() {
		return listKey;
	}
	public void setListKey(String listKey) {
		this.listKey = listKey;
	}
}
