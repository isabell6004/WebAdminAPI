package net.fashiongo.webadmin.model.pojo.response;

import java.util.List;

import org.springframework.data.domain.Page;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.fashiongo.webadmin.model.primary.Policy;
/**
 * 
 * @author DAHYE
 *
 */
public class GetPolicyManagementResponse {
	@JsonProperty("RecCnt")
	private Long recCnt;
	
	@JsonProperty("VPolicyList")
	private List<Policy> vpolicyList;

	public Long getRecCnt() {
		return recCnt;
	}

	public List<Policy> getVpolicyList() {
		return vpolicyList;
	}

	public void setRecCnt(Long recCnt) {
		this.recCnt = recCnt;
	}

	public void setVpolicyList(List<Policy> vpolicyList) {
		this.vpolicyList = vpolicyList;
	}
	
	
}
