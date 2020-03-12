package net.fashiongo.webadmin.data.model.buyer;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

@Getter
public class GetBuyerSearchWithHistory {
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
	
	@JsonProperty("oldemail")
	private String oldemail;

	@JsonProperty("oldemailpartialmatch")
	private Boolean oldemailpartialmatch;

}
