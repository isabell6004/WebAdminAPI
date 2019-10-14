package net.fashiongo.webadmin.data.model.franchise;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public class FranchiseBuyer {

	@JsonProperty("retailerId")
	private Integer retailerId;

	@JsonProperty("companyName")
	private String companyName;

	@JsonProperty("firstName")
	private String firstName;

	@JsonProperty("lastName")
	private String lastName;

	@JsonProperty("email")
	private String email;

	@JsonProperty("isMasterAccount")
	private boolean isMasterAccount;

	@JsonProperty("isSubAccount")
	private boolean isSubAccount;

	public FranchiseBuyer(Integer retailerId, String companyName, String firstName, String lastName, String email, boolean isMasterAccount, boolean isSubAccount) {
		this.retailerId = retailerId;
		this.companyName = companyName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.isMasterAccount = isMasterAccount;
		this.isSubAccount = isSubAccount;
	}

	public Integer getRetailerId() {
		return retailerId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public boolean getIsMasterAccount() {
		return isMasterAccount;
	}

	public boolean getIsSubAccount() {
		return isSubAccount;
	}
}
