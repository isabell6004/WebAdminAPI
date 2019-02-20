package net.fashiongo.webadmin.model.pojo.vendor.parameter;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @author DAHYE
 *
 */
public class GetVendorCreditCardListParameter implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(hidden=true)
	@JsonProperty("orderby")
	private String orderBy;

	public String getOrderBy() {
		return StringUtils.isEmpty(orderBy) ? null : orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	
	
}
