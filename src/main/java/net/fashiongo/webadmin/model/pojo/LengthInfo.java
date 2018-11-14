package net.fashiongo.webadmin.model.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Nayeon Kim
 */
public class LengthInfo {
	@JsonProperty("LengthID")
	private Integer lengthID;
	@JsonProperty("LengthName")
	private String lengthName;
	@JsonProperty("Active")
	private Boolean active;
	
	public Integer getLengthID() {
		return lengthID;
	}
	public void setLengthID(Integer lengthID) {
		this.lengthID = lengthID;
	}
	public String getLengthName() {
		return lengthName;
	}
	public void setLengthName(String lengthName) {
		this.lengthName = lengthName;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
}
