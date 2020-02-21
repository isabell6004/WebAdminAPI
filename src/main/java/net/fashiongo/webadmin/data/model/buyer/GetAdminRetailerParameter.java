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
	private String currentstatus;

	@JsonProperty("location")
	private String location;

	@JsonProperty("state")
	private String state;

	@JsonProperty("country")
	private String country;

	@JsonProperty("showid")
	private String showid;

	@JsonProperty("registerfrom")
	private String registerfrom;

	@JsonProperty("registerto")
	private String registerto;

	@JsonProperty("logincountfrom")
	private String logincountfrom;

	@JsonProperty("logincountto")
	private String logincountto;

	@JsonProperty("loginfrom")
	private String loginfrom;

	@JsonProperty("loginto")
	private String loginto;

	@JsonProperty("ordercountfrom")
	private String ordercountfrom;

	@JsonProperty("ordercountto")
	private String ordercountto;

	@JsonProperty("checkoutfrom")
	private String checkoutfrom;

	@JsonProperty("checkoutto")
	private String checkoutto;

	@JsonProperty("ordervendorcountfrom")
	private String ordervendorcountfrom;

	@JsonProperty("ordervendorcountto")
	private String ordervendorcountto;

	@JsonProperty("wholesalerid")
	private String wholesalerid;

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
	private String orderamountfrom;

	@JsonProperty("orderamountto")
	private String orderamountto;
	
	@JsonProperty("buyerclass")
	private Integer buyerclass;	
}
