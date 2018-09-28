package net.fashiongo.webadmin.model.pojo.parameter;

import io.swagger.annotations.ApiModelProperty;

public class GetCountryStatesParameter {
	@ApiModelProperty(required = false, example="us")
	private String countryabbrev;

	public String getCountryabbrev() {
		return countryabbrev;
	}

	public void setCountryabbrev(String countryabbrev) {
		this.countryabbrev = countryabbrev;
	}
	
}
