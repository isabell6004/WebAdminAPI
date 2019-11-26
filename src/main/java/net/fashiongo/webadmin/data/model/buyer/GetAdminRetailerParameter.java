package net.fashiongo.webadmin.data.model.buyer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class GetAdminRetailerParameter {

	@JsonProperty("pagenum")
	private Integer pagenum;

	@JsonProperty("pagesize")
	private Integer pagesize;

	@JsonProperty("retailerid")
	private Integer retailerid;

	@JsonProperty("orderby")
	private String orderby;

	@JsonProperty("userid")
	private String userid;

	@JsonProperty("useridpartialmatch")
	private Boolean useridpartialmatch;

	@JsonProperty("active")
	private Boolean active;

	@JsonProperty("online")
	private Boolean online;

	@JsonProperty("documentupload")
	private String documentupload;

	@JsonProperty("s")
	private Boolean s;

	@JsonProperty("in1")
	private Boolean in1;

	@JsonProperty("in2")
	private Boolean in2;

	@JsonProperty("o")
	private Boolean o;

	@JsonProperty("currentstatus")
	private Integer currentstatus;

	@JsonProperty("location")
	private String location;

	@JsonProperty("state")
	private String state;

	@JsonProperty("country")
	private String country;

	@JsonProperty("showid")
	private Integer showid;

	@JsonProperty("registerfrom")
	private String registerfrom;

	@JsonProperty("registerto")
	private String registerto;

	@JsonProperty("logincountfrom")
	private Integer logincountfrom;

	@JsonProperty("logincountto")
	private Integer logincountto;

	@JsonProperty("loginfrom")
	private String loginfrom;

	@JsonProperty("loginto")
	private String loginto;

	@JsonProperty("ordercountfrom")
	private Integer ordercountfrom;

	@JsonProperty("ordercountto")
	private Integer ordercountto;

	@JsonProperty("checkoutfrom")
	private String checkoutfrom;

	@JsonProperty("checkoutto")
	private String checkoutto;

	@JsonProperty("ordervendorcountfrom")
	private Integer ordervendorcountfrom;

	@JsonProperty("ordervendorcountto")
	private Integer ordervendorcountto;

	@JsonProperty("wholesalerid")
	private Integer wholesalerid;

	@JsonProperty("csv")
	private Boolean csv;

	@JsonProperty("companyname")
	private String companyname;

	@JsonProperty("companynamepartialmatch")
	private Boolean companynamepartialmatch;

	@JsonProperty("companynamestartswith")
	private Boolean companynamestartswith;


	@JsonProperty("firstname")
	private String firstname;

	@JsonProperty("firstnamepartialmatch")
	private Boolean firstnamepartialmatch;

	@JsonProperty("lastname")
	private String lastname;

	@JsonProperty("lastnamepartialmatch")
	private Boolean lastnamepartialmatch;

	@JsonProperty("orderamountfrom")
	private BigDecimal orderamountfrom;

	@JsonProperty("orderamountto")
	private BigDecimal orderamountto;
}
