package net.fashiongo.webadmin.model.pojo.vendor;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author Reo
 *
 */
public class Country {
	@JsonProperty("CountryName")
	private Integer countryName;

	public Integer getCountryName() {
		return countryName;
	}

	public void setCountryName(Integer countryName) {
		this.countryName = countryName;
	}
	
	
}
