package net.fashiongo.webadmin.data.model.buyer;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import net.fashiongo.webadmin.controller.validator.SQLInjectionSafeWithKeywordsFilter;

import javax.validation.constraints.Pattern;

@Getter
public class GetBuyerSearchWithHistory {

	private static final String ALLOW_PATTERN = "^$|[a-zA-Z0-9\\s =/:!&,-.?_\']+$";
	private static final String ALLOW_PATTERN_MESSAGE = "Special character not allowed";

	@JsonProperty("pagenum")
	private Integer pagenum;

	@JsonProperty("pagesize")
	private Integer pagesize;

	@JsonProperty("retailerid")
	private Integer retailerid;

	@JsonProperty("orderby")
	@SQLInjectionSafeWithKeywordsFilter
	@Pattern(regexp = ALLOW_PATTERN, message = ALLOW_PATTERN_MESSAGE)
	private String orderby;

	@JsonProperty("userid")
	private String userid;

	@JsonProperty("useridpartialmatch")
	private Boolean useridpartialmatch;
	
	@JsonProperty("companyname")
	@SQLInjectionSafeWithKeywordsFilter
	@Pattern(regexp = ALLOW_PATTERN, message = ALLOW_PATTERN_MESSAGE)
	private String companyname;

	@JsonProperty("companynamepartialmatch")
	private Boolean companynamepartialmatch;

	@JsonProperty("companynamestartswith")
	private Boolean companynamestartswith;

	@JsonProperty("firstname")
	@SQLInjectionSafeWithKeywordsFilter
	@Pattern(regexp = ALLOW_PATTERN, message = ALLOW_PATTERN_MESSAGE)
	private String firstname;

	@JsonProperty("firstnamepartialmatch")
	private Boolean firstnamepartialmatch;

	@JsonProperty("lastname")
	@SQLInjectionSafeWithKeywordsFilter
	@Pattern(regexp = ALLOW_PATTERN, message = ALLOW_PATTERN_MESSAGE)
	private String lastname;

	@JsonProperty("lastnamepartialmatch")
	private Boolean lastnamepartialmatch;
	
	@JsonProperty("oldemail")
	@SQLInjectionSafeWithKeywordsFilter
	private String oldemail;

	@JsonProperty("oldemailpartialmatch")
	private Boolean oldemailpartialmatch;

}
