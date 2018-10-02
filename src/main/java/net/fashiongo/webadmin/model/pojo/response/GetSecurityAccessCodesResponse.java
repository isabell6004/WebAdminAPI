package net.fashiongo.webadmin.model.pojo.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.fashiongo.webadmin.model.pojo.SecurityAccessCodes;

/**
 * @author JungHwan
 */
public class GetSecurityAccessCodesResponse {
	@JsonProperty("Table")
	private List<SecurityAccessCodes> securityAccessCodes;

	public List<SecurityAccessCodes> getSecurityAccessCodes() {
		return securityAccessCodes;
	}

	public void setSecurityAccessCodes(List<SecurityAccessCodes> securityAccessCodes) {
		this.securityAccessCodes = securityAccessCodes;
	}
	
}
