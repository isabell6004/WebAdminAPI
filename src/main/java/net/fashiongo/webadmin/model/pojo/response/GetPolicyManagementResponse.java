package net.fashiongo.webadmin.model.pojo.response;

import java.util.List;

import org.springframework.data.domain.Page;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.fashiongo.webadmin.model.primary.Policy;

public class GetPolicyManagementResponse {
	@JsonProperty("RecCnt")
	private Long recCnt;
	
	@JsonProperty("VPolicyList")
	private Page<Policy> vpolicyList;

	public Long getRecCnt() {
		return recCnt;
	}

	public Page<Policy> getVpolicyList() {
		return vpolicyList;
	}

	public void setRecCnt(Long recCnt) {
		this.recCnt = recCnt;
	}

	public void setVpolicyList(Page<Policy> vpolicyList) {
		this.vpolicyList = vpolicyList;
	}
	
	
}
