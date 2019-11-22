package net.fashiongo.webadmin.data.model.buyer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class BillingInfo {

	@JsonProperty(value = "RetailerID")
	private Integer RetailerID;

	@JsonProperty(value = "BillStreetNo")
	private String BillStreetNo;

	@JsonProperty(value = "BillStreetNo2")
	private String BillStreetNo2;

	@JsonProperty(value = "BillCity")
	private String BillCity;

	@JsonProperty(value = "BillSTATE")
	private String BillSTATE;

	@JsonProperty(value = "BillCountry")
	private String BillCountry;

	@JsonProperty(value = "BillCountryID")
	private Integer BillCountryID;

	@JsonProperty(value = "BillZipcode")
	private String BillZipcode;

	@JsonProperty(value = "BillPhone")
	private String BillPhone;

	@JsonProperty(value = "BillFax")
	private String BillFax;
}
