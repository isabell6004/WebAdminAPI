package net.fashiongo.webadmin.model.primary;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author Reo
 *
 */
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "[vwVendorBlocked]")
public class VwVendorBlocked {
	@Id
	@Column(name = "BlockID")
	@JsonProperty("BlockID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer blockID;
	
	@Column(name = "WholeSalerID")
	@JsonProperty("WholeSalerID")
	private Integer wholeSalerID;
	
	@Column(name = "BlockReasonID")
	@JsonProperty("BlockReasonID")
	private Integer blockReasonID;
	
	@Column(name = "BlockedOn")
	@JsonProperty("BlockedOn")
	private LocalDateTime blockedOn;
	
	@Column(name = "BlockedBy")
	@JsonProperty("BlockedBy")
	private String blockedBy;
	
	@Column(name = "CompanyName")
	@JsonProperty("CompanyName")
	private String companyName;
	
	@Column(name = "BlockReasonTitle")
	@JsonProperty("BlockReasonTitle")
	private String blockReasonTitle;
	
	@Column(name = "BlockReasonDetail")
	@JsonProperty("BlockReasonDetail")
	private String blockReasonDetail;

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

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getBlockReasonTitle() {
		return blockReasonTitle;
	}

	public void setBlockReasonTitle(String blockReasonTitle) {
		this.blockReasonTitle = blockReasonTitle;
	}

	public String getBlockReasonDetail() {
		return blockReasonDetail;
	}

	public void setBlockReasonDetail(String blockReasonDetail) {
		this.blockReasonDetail = blockReasonDetail;
	}
	
	
}
