package net.fashiongo.webadmin.model.primary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author Reo
 *
 */
@Entity
@Table(name = "Code_Pattern")
public class CodePattern {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("PatternID")
	@Column(name = "PatternID")
	private Integer patternID;
	
	@JsonProperty("PatternName")
	@Column(name = "PatternName")
	private String patternName;
	
	@JsonProperty("Active")
	@Column(name = "Active")
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
