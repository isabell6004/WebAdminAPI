package net.fashiongo.webadmin.model.pojo.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.fashiongo.webadmin.model.pojo.PolicyDetail;
import net.fashiongo.webadmin.model.pojo.Total;
/**
 * 
 * @author DAHYE
 *
 */
public class GetPolicyDetailResponse {
	@JsonProperty("Table")
	private Total total;
	@JsonProperty("Table1")
	private List<PolicyDetail> policyDetail;
	public Total getTotal() {
		return total;
	}
	public void setTotal(Total total) {
		this.total = total;
	}
	public List<PolicyDetail> getPolicyDetail() {
		return policyDetail;
	}
	public void setPolicyDetail(List<PolicyDetail> policyDetail) {
		this.policyDetail = policyDetail;
	}
	
	
}
