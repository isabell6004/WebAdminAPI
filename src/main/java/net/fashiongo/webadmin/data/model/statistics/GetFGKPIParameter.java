package net.fashiongo.webadmin.data.model.statistics;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import net.fashiongo.webadmin.controller.validator.SQLInjectionSafeWithKeywordsFilter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
public class GetFGKPIParameter {

	private static final String ALLOW_PATTERN = "^$|[a-zA-Z0-9\\s /:!&,-.?_\']+$";
	private static final String ALLOW_PATTERN_MESSAGE = "Special character not allowed";

	@NotNull
	@JsonProperty("pn")
	private Integer pn;

	@NotNull
	@JsonProperty("ps")
	private Integer ps;

	@NotNull
	@JsonProperty("unit")
	private Integer unit;

	@SQLInjectionSafeWithKeywordsFilter
	@Pattern(regexp = ALLOW_PATTERN, message = ALLOW_PATTERN_MESSAGE)
	@JsonProperty("df")
	private String df;

	@SQLInjectionSafeWithKeywordsFilter
	@Pattern(regexp = ALLOW_PATTERN, message = ALLOW_PATTERN_MESSAGE)
	@JsonProperty("dt")
	private String dt;

	@SQLInjectionSafeWithKeywordsFilter
	@Pattern(regexp = ALLOW_PATTERN, message = ALLOW_PATTERN_MESSAGE)
	@JsonProperty("orderBy")
	private String orderBy;

}
