package net.fashiongo.webadmin.model.pojo.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.fashiongo.webadmin.model.pojo.Total;
import net.fashiongo.webadmin.model.primary.PolicyAgreement;
/**
 * 
 * @author DAHYE
 *
 */
public class GetPolicyDetailResponse {
	@JsonProperty("Table")
	private List<Total> total;
	@JsonProperty("Table1")
	private List<PolicyAgreement> policyDetail;
	public List<Total> getTotal() {
		return total;
	}
	public void setTotal(List<Total> total) {
		this.total = total;
	}
	public List<PolicyAgreement> getPolicyDetail() {
		return policyDetail;
	}
	public void setPolicyDetail(List<PolicyAgreement> policyDetail) {
		this.policyDetail = policyDetail;
	}
	
	
}
