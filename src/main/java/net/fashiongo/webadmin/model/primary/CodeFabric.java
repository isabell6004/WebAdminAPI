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
@Table(name = "Code_Fabric")
public class CodeFabric {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("FabricID")
	@Column(name = "FabricID")
	private Integer fabricID;
	
	@JsonProperty("FabricName")
	@Column(name = "FabricName")
	private String fabricName;
	
	@JsonProperty("Active")
	@Column(name = "Active")
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
