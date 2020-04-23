package net.fashiongo.webadmin.data.model.buyer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import net.fashiongo.webadmin.controller.validator.SQLInjectionSafeWithKeywordsFilter;

import javax.validation.constraints.Pattern;

@Getter
public class GetModifiedByBuyerParameter {

	private static final String ALLOW_PATTERN = "^$|[a-zA-Z0-9\\s =/:!&,-.?_\']+$";
	private static final String ALLOW_PATTERN_MESSAGE = "Special character not allowed";

	@JsonProperty("fromdate")
	@SQLInjectionSafeWithKeywordsFilter
	@Pattern(regexp = ALLOW_PATTERN, message = ALLOW_PATTERN_MESSAGE)
	private String fromdate;
}
