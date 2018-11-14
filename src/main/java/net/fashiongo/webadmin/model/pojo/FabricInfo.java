package net.fashiongo.webadmin.model.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Nayeon Kim
 */
public class FabricInfo {
	@JsonProperty("FabricID")
	private Integer fabricID;
	@JsonProperty("FabricName")
	private String fabricName;
	@JsonProperty("Active")
	private Boolean active;
	
	public Integer getFabricID() {
		return fabricID;
	}
	public void setFabricID(Integer fabricID) {
		this.fabricID = fabricID;
	}
	public String getFabricName() {
		return fabricName;
	}
	public void setFabricName(String fabricName) {
		this.fabricName = fabricName;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
}
