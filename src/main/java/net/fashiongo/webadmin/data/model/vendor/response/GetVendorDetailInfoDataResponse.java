package net.fashiongo.webadmin.data.model.vendor.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import net.fashiongo.webadmin.data.model.vendor.Country;
import net.fashiongo.webadmin.data.model.vendor.VendorCompanyType;
import net.fashiongo.webadmin.data.model.vendor.VendorDetailDate;

import java.util.List;

@Getter
@Builder
public class GetVendorDetailInfoDataResponse {

	@JsonProperty("SessionUsrID")
	private String sessionUsrID;

	@JsonProperty("VendorDefaultInfo")
	private VendorDetailDate vendorDefaultInfo;

	@JsonProperty("FgAeName")
	private String fgAeName;

	@JsonProperty("CompanyTypeList")
	private List<VendorCompanyType> vendorCompanyTypeList;

	@JsonProperty("CountryList")
	private List<Country> countryList;
}
