package net.fashiongo.webadmin.model.pojo.common.parameter;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
/**
 * 
 * @author DAHYE
 *
 */
@SuppressWarnings("serial")
public class GetCountryStatesParameter implements Serializable {
	@ApiModelProperty(required = false, example="us")
	private String countryAbbrev;
	public String getCountryAbbrev() {
		return countryAbbrev;
	}
	public void setCountryAbbrev(String countryAbbrev) {
		this.countryAbbrev = countryAbbrev;
	}
}
