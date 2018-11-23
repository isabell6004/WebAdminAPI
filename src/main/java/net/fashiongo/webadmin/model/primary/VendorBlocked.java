package net.fashiongo.webadmin.model.primary;

import java.io.Serializable;
import java.time.LocalDateTime;

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
 * @author Reo
 *
 */
@Entity
@Table(name = "Vendor_Blocked")
public class VendorBlocked implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "BlockID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@JsonProperty("BlockID")
	private Integer blockID;
	
	@JsonProperty("WholeSalerID")
	private Integer wholeSalerID;
	
	@JsonProperty("BlockReasonID")
	private Integer blockReasonID;
	
	@JsonProperty("BlockedOn")
	private LocalDateTime blockedOn;
	
	@JsonProperty("BlockedBy")
	private String blockedBy;

	public Integer getBlockID() {
		return blockID;
	}

	public void setBlockID(Integer blockID) {
		this.blockID = blockID;
	}

	public Integer getWholeSalerID() {
		return wholeSalerID;
	}

	public void setWholeSalerID(Integer wholeSalerID) {
		this.wholeSalerID = wholeSalerID;
	}

	public Integer getBlockReasonID() {
		return blockReasonID;
	}

	public void setBlockReasonID(Integer blockReasonID) {
		this.blockReasonID = blockReasonID;
	}

	public LocalDateTime getBlockedOn() {
		return blockedOn;
	}

	public void setBlockedOn(LocalDateTime blockedOn) {
		this.blockedOn = blockedOn;
	}

	public String getBlockedBy() {
		return blockedBy;
	}

	public void setBlockedBy(String blockedBy) {
		this.blockedBy = blockedBy;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
