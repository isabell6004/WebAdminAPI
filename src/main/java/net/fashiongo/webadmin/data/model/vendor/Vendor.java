
package net.fashiongo.webadmin.data.model.vendor;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class Vendor {

	@JsonProperty("WholeSalerID")
	private Integer wholeSalerId;

	@JsonProperty("CompanyName")
	private String companyName;

	public Vendor(Integer wholeSalerId, String companyName) {
		this.wholeSalerId = wholeSalerId;
		this.companyName = companyName;
	}
}
