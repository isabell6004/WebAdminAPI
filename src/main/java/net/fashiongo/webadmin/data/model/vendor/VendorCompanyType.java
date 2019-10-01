package net.fashiongo.webadmin.data.model.vendor;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class VendorCompanyType {

	@JsonProperty("CompanyTypeID")
	private Integer companyTypeID;

	@JsonProperty("CompanyTypeName")
	private String companyTypeName;

	@JsonProperty("Active")
	private Boolean active;
}
