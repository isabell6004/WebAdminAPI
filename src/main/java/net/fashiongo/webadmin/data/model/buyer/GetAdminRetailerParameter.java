package net.fashiongo.webadmin.data.model.buyer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.rkpunjal.sqlsafe.SQLInjectionSafe;
import lombok.Getter;
import net.fashiongo.webadmin.controller.validator.SQLInjectionSafeWithKeywordsFilter;

import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

@Getter
public class GetAdminRetailerParameter {

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
	@SQLInjectionSafeWithKeywordsFilter
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
	@SQLInjectionSafeWithKeywordsFilter
	@Pattern(regexp = ALLOW_PATTERN, message = ALLOW_PATTERN_MESSAGE)
	private String currentstatus;

	@JsonProperty("location")
	@SQLInjectionSafeWithKeywordsFilter
	@Pattern(regexp = ALLOW_PATTERN, message = ALLOW_PATTERN_MESSAGE)
	private String location;

	@JsonProperty("state")
	@SQLInjectionSafeWithKeywordsFilter
	@Pattern(regexp = ALLOW_PATTERN, message = ALLOW_PATTERN_MESSAGE)
	private String state;

	@JsonProperty("country")
	@SQLInjectionSafeWithKeywordsFilter
	@Pattern(regexp = ALLOW_PATTERN, message = ALLOW_PATTERN_MESSAGE)
	private String country;

	@JsonProperty("showid")
	@SQLInjectionSafeWithKeywordsFilter
	@Pattern(regexp = ALLOW_PATTERN, message = ALLOW_PATTERN_MESSAGE)
	private String showid;

	@JsonProperty("registerfrom")
	@SQLInjectionSafeWithKeywordsFilter
	@Pattern(regexp = ALLOW_PATTERN, message = ALLOW_PATTERN_MESSAGE)
	private String registerfrom;

	@JsonProperty("registerto")
	@SQLInjectionSafeWithKeywordsFilter
	@Pattern(regexp = ALLOW_PATTERN, message = ALLOW_PATTERN_MESSAGE)
	private String registerto;

	@JsonProperty("logincountfrom")
	@SQLInjectionSafeWithKeywordsFilter
	@Pattern(regexp = ALLOW_PATTERN, message = ALLOW_PATTERN_MESSAGE)
	private String logincountfrom;

	@JsonProperty("logincountto")
	@SQLInjectionSafeWithKeywordsFilter
	@Pattern(regexp = ALLOW_PATTERN, message = ALLOW_PATTERN_MESSAGE)
	private String logincountto;

	@JsonProperty("loginfrom")
	@SQLInjectionSafeWithKeywordsFilter
	@Pattern(regexp = ALLOW_PATTERN, message = ALLOW_PATTERN_MESSAGE)
	private String loginfrom;

	@JsonProperty("loginto")
	@SQLInjectionSafeWithKeywordsFilter
	@Pattern(regexp = ALLOW_PATTERN, message = ALLOW_PATTERN_MESSAGE)
	private String loginto;

	@JsonProperty("ordercountfrom")
	@SQLInjectionSafeWithKeywordsFilter
	@Pattern(regexp = ALLOW_PATTERN, message = ALLOW_PATTERN_MESSAGE)
	private String ordercountfrom;

	@JsonProperty("ordercountto")
	@SQLInjectionSafeWithKeywordsFilter
	@Pattern(regexp = ALLOW_PATTERN, message = ALLOW_PATTERN_MESSAGE)
	private String ordercountto;

	@JsonProperty("checkoutfrom")
	@SQLInjectionSafeWithKeywordsFilter
	@Pattern(regexp = ALLOW_PATTERN, message = ALLOW_PATTERN_MESSAGE)
	private String checkoutfrom;

	@JsonProperty("checkoutto")
	@SQLInjectionSafeWithKeywordsFilter
	@Pattern(regexp = ALLOW_PATTERN, message = ALLOW_PATTERN_MESSAGE)
	private String checkoutto;

	@JsonProperty("ordervendorcountfrom")
	@SQLInjectionSafeWithKeywordsFilter
	@Pattern(regexp = ALLOW_PATTERN, message = ALLOW_PATTERN_MESSAGE)
	private String ordervendorcountfrom;

	@JsonProperty("ordervendorcountto")
	@SQLInjectionSafeWithKeywordsFilter
	@Pattern(regexp = ALLOW_PATTERN, message = ALLOW_PATTERN_MESSAGE)
	private String ordervendorcountto;

	@JsonProperty("wholesalerid")
	@SQLInjectionSafeWithKeywordsFilter
	@Pattern(regexp = ALLOW_PATTERN, message = ALLOW_PATTERN_MESSAGE)
	private String wholesalerid;

	@JsonProperty("csv")
	private Boolean csv;

	@JsonProperty("companyname")
	@SQLInjectionSafe
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

	@JsonProperty("orderamountfrom")
	@SQLInjectionSafeWithKeywordsFilter
	@Pattern(regexp = ALLOW_PATTERN, message = ALLOW_PATTERN_MESSAGE)
	private String orderamountfrom;

	@JsonProperty("orderamountto")
	@SQLInjectionSafeWithKeywordsFilter
	@Pattern(regexp = ALLOW_PATTERN, message = ALLOW_PATTERN_MESSAGE)
	private String orderamountto;
	
	@JsonProperty("buyerclass")
	private Integer buyerclass;	
}
