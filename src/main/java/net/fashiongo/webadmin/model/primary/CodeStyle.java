package net.fashiongo.webadmin.model.primary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "Code_Style")
public class CodeStyle {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("StyleID")
	@Column(name = "StyleID")
	private Integer styleID;
	
	@JsonProperty("StyleName")
	@Column(name = "StyleName")
	private String styleName;
	
	@JsonProperty("Active")
	@Column(name = "Active")
	private Boolean active;

	public Integer getStyleID() {
		return styleID;
	}

	public void setStyleID(Integer styleID) {
		this.styleID = styleID;
	}

	public String getStyleName() {
		return styleName;
	}

	public void setStyleName(String styleName) {
		this.styleName = styleName;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
}
