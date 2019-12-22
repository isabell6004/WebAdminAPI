package net.fashiongo.webadmin.data.model.sitemgmt;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class SitemgmtGetItemsParameter {

	@JsonProperty("pagenum")
	private Integer pagenum;

	@JsonProperty("pagesize")
	private Integer pagesize;

	@JsonProperty("fgcat")
	private String fgcat;

	@JsonProperty("vendorid")
	private String vendorid;

	@JsonProperty("selectedcategoryid")
	private String selectedcategoryid;

	@JsonProperty("searchitemtxt")
	private String searchitemtxt;

	@JsonProperty("companytypeid1")
	private Boolean companytypeid1;

	@JsonProperty("companytypeid2")
	private Boolean companytypeid2;

	@JsonProperty("companytypeid3")
	private Boolean companytypeid3;

	@JsonProperty("inventory")
	private String inventory;
}
