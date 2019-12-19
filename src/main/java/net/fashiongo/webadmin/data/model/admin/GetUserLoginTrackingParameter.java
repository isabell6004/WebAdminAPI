package net.fashiongo.webadmin.data.model.admin;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class GetUserLoginTrackingParameter {

	@JsonProperty("usertype")
	private String usertype;

	@JsonProperty("pagenum")
	private Integer pagenum;

	@JsonProperty("pagesize")
	private Integer pagesize;

	@JsonProperty("sortfield")
	private String sortfield;

	@JsonProperty("sortdir")
	private String sortdir;

	@JsonProperty("username")
	private String username;

	@JsonProperty("companyname")
	private String companyname;

	@JsonProperty("sdate")
	private String sdate;

	@JsonProperty("edate")
	private String edate;

	@JsonProperty("ip")
	private String ip;
}
