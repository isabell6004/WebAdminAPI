package net.fashiongo.webadmin.model.primary;

import java.time.LocalDateTime;

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
@Table(name = "Entity_ActionLog")
public class EntityActionLog {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("LogID")
	@Column(name = "LogID")
	private Integer logID;
	
	@JsonProperty("EntityTypeID")
	@Column(name = "EntityTypeID")
	private Integer entityTypeID;
	
	@JsonProperty("EntityID")
	@Column(name = "EntityID")
	private Integer entityID;
	
	@JsonProperty("ActionID")
	@Column(name = "ActionID")
	private Integer actionID;
	
	@JsonProperty("ActedOn")
	@Column(name = "ActedOn")
	private LocalDateTime actedOn;
	
	@JsonProperty("ActedBy")
	@Column(name = "ActedBy")
	private String actedBy;
	
	@JsonProperty("Remark")
	@Column(name = "Remark")
	private String remark;

	public Integer getLogID() {
		return logID;
	}

	public void setLogID(Integer logID) {
		this.logID = logID;
	}

	public Integer getEntityTypeID() {
		return entityTypeID;
	}

	public void setEntityTypeID(Integer entityTypeID) {
		this.entityTypeID = entityTypeID;
	}

	public Integer getEntityID() {
		return entityID;
	}

	public void setEntityID(Integer entityID) {
		this.entityID = entityID;
	}

	public Integer getActionID() {
		return actionID;
	}

	public void setActionID(Integer actionID) {
		this.actionID = actionID;
	}

	public LocalDateTime getActedOn() {
		return actedOn;
	}

	public void setActedOn(LocalDateTime actedOn) {
		this.actedOn = actedOn;
	}

	public String getActedBy() {
		return actedBy;
	}

	public void setActedBy(String actedBy) {
		this.actedBy = actedBy;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
