package net.fashiongo.webadmin.data.model.buyer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class SetSavedListParameter {

	@JsonProperty("savedid")
	private Integer savedid;

	@JsonProperty("savedname")
	private String savedname;

	@JsonProperty("savedtype")
	private String savedtype;

	@JsonProperty("display")
	private String display;

	@JsonProperty("filter")
	private String filter;

	@JsonProperty("remark")
	private String remark;

}
