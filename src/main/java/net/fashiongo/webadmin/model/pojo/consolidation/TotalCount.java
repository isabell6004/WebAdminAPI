package net.fashiongo.webadmin.model.pojo.consolidation;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class TotalCount {
	@JsonProperty("RecCnt")
	@Column(name = "RecCnt")	
	private Integer recCount;
}
