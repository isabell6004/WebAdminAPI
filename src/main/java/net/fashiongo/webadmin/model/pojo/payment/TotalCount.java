package net.fashiongo.webadmin.model.pojo.payment;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class TotalCount {
	@JsonProperty("TotalCount")
	private Integer totalCount;
}
