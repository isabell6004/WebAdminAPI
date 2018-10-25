package net.fashiongo.webadmin.model.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Nayeon Kim
 */
public class PatternInfo {
	@JsonProperty("PatternID")
	private Integer patternID;
	@JsonProperty("PatternName")
	private String patternName;
	@JsonProperty("Active")
	private Boolean active;
	
	public Integer getPatternID() {
		return patternID;
	}
	public void setPatternID(Integer patternID) {
		this.patternID = patternID;
	}
	public String getPatternName() {
		return patternName;
	}
	public void setPatternName(String patternName) {
		this.patternName = patternName;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
}
