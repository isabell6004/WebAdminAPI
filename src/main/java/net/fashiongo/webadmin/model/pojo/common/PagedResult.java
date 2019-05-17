package net.fashiongo.webadmin.model.pojo.common;

import java.util.List;

import lombok.Data;

@Data
public class PagedResult<T> {
	private SingleValueResult total;
	private Integer pageNumber;
	private List<T> records;
	private String listKey;
}
