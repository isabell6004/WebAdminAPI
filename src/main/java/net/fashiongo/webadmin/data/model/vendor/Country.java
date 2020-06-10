package net.fashiongo.webadmin.data.model.vendor;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Country {

	@JsonProperty("CountryName")
	private String countryName;

	@JsonProperty("CountryID")
	private Integer countryID;

}
