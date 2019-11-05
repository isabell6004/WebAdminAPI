package net.fashiongo.webadmin.data.model.buyer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class GetSavedListParameter {

	@JsonProperty("pagenum")
	private Integer pagenum;

	@JsonProperty("pagesize")
	private Integer pagesize;

	@JsonProperty("savedtype")
	private String savedtype;

	@JsonProperty("type")
	private String type;

	@JsonProperty("keyword")
	private String keyword;

	@JsonProperty("startdate")
	private String startdate;

	@JsonProperty("enddate")
	private String enddate;

	@JsonProperty("orderby")
	private String orderby;

}
