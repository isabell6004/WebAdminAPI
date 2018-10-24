package net.fashiongo.webadmin.model.pojo.parameter;

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
	private String countryabbrev;

	public String getCountryabbrev() {
		return countryabbrev;
	}

	public void setCountryabbrev(String countryabbrev) {
		this.countryabbrev = countryabbrev;
	}
	
}
