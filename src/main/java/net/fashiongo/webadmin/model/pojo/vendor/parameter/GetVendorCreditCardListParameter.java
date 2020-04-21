package net.fashiongo.webadmin.model.pojo.vendor.parameter;

import java.io.Serializable;

import net.fashiongo.webadmin.controller.validator.SQLInjectionSafeWithKeywordsFilter;
import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Pattern;

/**
 * 
 * @author DAHYE
 *
 */
public class GetVendorCreditCardListParameter implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String ALLOW_PATTERN = "^$|[a-zA-Z0-9\\s =/:!&,-.?_\']+$";
	private static final String ALLOW_PATTERN_MESSAGE = "Special character not allowed";
	
	@ApiModelProperty(hidden=true)
	@JsonProperty("orderby")
	@SQLInjectionSafeWithKeywordsFilter
	@Pattern(regexp = ALLOW_PATTERN, message = ALLOW_PATTERN_MESSAGE)
	private String orderBy;

	public String getOrderBy() {
		return StringUtils.isEmpty(orderBy) ? null : orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

}
