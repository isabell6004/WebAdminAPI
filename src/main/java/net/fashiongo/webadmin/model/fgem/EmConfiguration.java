package net.fashiongo.webadmin.model.fgem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Nayeon Kim
 */
@Entity
@Table(name = "\"EM.Configuration\"")
public class EmConfiguration {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ConfigID")
	@Column(name = "ConfigID")
	private Integer configID;
	
	@JsonProperty("ConfigType")
	@Column(name = "ConfigType")
	private String configType;
	
	@JsonProperty("ConfigValue")
	@Column(name = "ConfigValue")
	private String configValue;

	public Integer getConfigID() {
		return configID;
	}

	public void setConfigID(Integer configID) {
		this.configID = configID;
	}

	public String getConfigType() {
		return configType;
	}

	public void setConfigType(String configType) {
		this.configType = configType;
	}

	public String getConfigValue() {
		return configValue;
	}

	public void setConfigValue(String configValue) {
		this.configValue = configValue;
	}
}
