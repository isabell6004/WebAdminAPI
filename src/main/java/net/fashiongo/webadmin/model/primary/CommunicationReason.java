package net.fashiongo.webadmin.model.primary;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author DAHYE
 *
 */
@Entity
@Table(name = "List_CommunicationReason")
public class CommunicationReason {
	@Id
	@Basic(optional = false)
	@NotNull
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ReasonID")
	private Integer reasonID;
	
	@Column(name = "Reason")
	private String reason;
	
	@Column(name = "ParentID")
	private Integer parentID;

	@Column(name = "Active")
	private Boolean active;

	@JsonProperty("ReasonID")
	public Integer getReasonID() {
		return reasonID;
	}

	@JsonProperty("reasonid")
	public void setReasonID(Integer reasonID) {
		this.reasonID = reasonID;
	}

	@JsonProperty("Reason")
	public String getReason() {
		return reason;
	}

	@JsonProperty("reason")
	public void setReason(String reason) {
		this.reason = reason;
	}

	@JsonProperty("ParentID")
	public Integer getParentID() {
		return parentID;
	}

	@JsonProperty("parentid")
	public void setParentID(Integer parentID) {
		this.parentID = parentID;
	}

	@JsonProperty("Active")
	public Boolean getActive() {
		return active;
	}

	@JsonProperty("active")
	public void setActive(Boolean active) {
		this.active = active;
	}

	
}
