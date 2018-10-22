package net.fashiongo.webadmin.model.photostudio;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import net.fashiongo.common.conversion.LocalDateTimeConverter;
import net.fashiongo.common.dal.IPersistent;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Log_Photo_Action")
public class LogPhotoAction implements IPersistent, Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ActionID")
	private Integer actionID;
	public Integer getActionID() {
		return actionID;
	}

	public void setActionID(Integer actionID) {
		this.actionID = actionID;
	}

	@Column(name = "ActionType")
	private Integer actionType;
	public Integer getActionType() {
		return actionType;
	}

	public void setActionType(Integer actionType) {
		this.actionType = actionType;
	}

	@Column(name = "OrderID")
	private Integer orderID;
	public Integer getOrderID() {
		return orderID;
	}

	public void setOrderID(Integer orderID) {
		this.orderID = orderID;
	}

	@Column(name = "ItemQty")
	private Integer itemQty;
	public Integer getItemQty() {
		return itemQty;
	}

	public void setItemQty(Integer itemQty) {
		this.itemQty = itemQty;
	}

	@Column(name = "DroppedBy")
	private String droppedBy;
	public String getDroppedBy() {
		return droppedBy;
	}

	public void setDroppedBy(String droppedBy) {
		this.droppedBy = droppedBy;
	}
	@JsonIgnore
	@Column(name = "CreatedOn", updatable = false)
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime createdOnDate;
	public LocalDateTime getCreatedOnDate() {
		return createdOnDate;
	}

	public void setCreatedOnDate(LocalDateTime createdOnDate) {
		this.createdOnDate = createdOnDate;
	}

	@Transient
	private String createdOn;
	public String getCreatedOn() {
		return createdOnDate != null ? createdOnDate.toString() : null;
	}

	@Column(name = "CreatedBy")
	private String createdBy;
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
}
