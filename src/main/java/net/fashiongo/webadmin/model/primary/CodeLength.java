package net.fashiongo.webadmin.model.primary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "Code_Length")
public class CodeLength {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("LengthID")
	@Column(name = "LengthID")
	private Integer lengthID;
	
	@JsonProperty("LengthName")
	@Column(name = "LengthName")
	private String lengthName;
	
	@JsonProperty("Active")
	@Column(name = "Active")
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
