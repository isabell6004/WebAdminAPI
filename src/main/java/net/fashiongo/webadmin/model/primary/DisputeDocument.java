/**
 * 
 */
package net.fashiongo.webadmin.model.primary;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.fashiongo.common.conversion.LocalDateTimeConverter;

/**
 * @author Brian
 *
 */
@Entity
@Table(name = "Payment_Dispute_Document")
public class DisputeDocument {
	@Id
	@Column(name = "DisputeID")
	private String disputeId;

	public String getDisputeId() {
		return disputeId;
	}

	public void setDisputeId(String disputeId) {
		this.disputeId = disputeId;
	}
	
	@Column(name = "FileName1")
	private String fileName1;

	
	public String getFileName1() {
		return fileName1;
	}

	public void setFileName1(String fileName1) {
		this.fileName1 = fileName1;
	}
	
	@Column(name = "FileName2")
	private String fileName2;

	public String getFileName2() {
		return fileName2;
	}

	public void setFileName2(String fileName2) {
		this.fileName2 = fileName2;
	}
	
	@Column(name = "FileName3")
	private String fileName3;
	
	public String getFileName3() {
		return fileName3;
	}

	public void setFileName3(String fileName3) {
		this.fileName3 = fileName3;
	}

	@Column(name = "FileName4")
	private String fileName4;
	
	public String getFileName4() {
		return fileName4;
	}

	public void setFileName4(String fileName4) {
		this.fileName4 = fileName4;
	}

	@Column(name = "FileName5")
	private String fileName5;
	
	public String getFileName5() {
		return fileName5;
	}

	public void setFileName5(String fileName5) {
		this.fileName5 = fileName5;
	}

	@Column(name = "FileName6")
	private String fileName6;
	
	public String getFileName6() {
		return fileName6;
	}

	public void setFileName6(String fileName6) {
		this.fileName6 = fileName6;
	}

	@Column(name = "FileName7")
	private String fileName7;
	
	public String getFileName7() {
		return fileName7;
	}

	public void setFileName7(String fileName7) {
		this.fileName7 = fileName7;
	}

	@Column(name = "FileName8")
	private String fileName8;

	public String getFileName8() {
		return fileName8;
	}

	public void setFileName8(String fileName8) {
		this.fileName8 = fileName8;
	}
	
	@Column(name = "IsAccepted")
	private Boolean isAccepted;

	public Boolean getIsAccepted() {
		return isAccepted;
	}

	public void setIsAccepted(Boolean isAccepted) {
		this.isAccepted = isAccepted;
	}

	@Column(name = "AcceptedOn")
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime acceptedOn;

	public LocalDateTime getAcceptedOn() {
		return acceptedOn;
	}

	public void setAcceptedOn(LocalDateTime acceptedOn) {
		this.acceptedOn = acceptedOn;
	}
	
	@Column(name = "AcceptedBy")
	private String acceptedBy;

	public String getAcceptedBy() {
		return acceptedBy;
	}

	public void setAcceptedBy(String acceptedBy) {
		this.acceptedBy = acceptedBy;
	}

	@Column(name = "CreatedOn")
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime createdOn;
	
	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}

	@Column(name = "CreatedBy")
	private String createdBy;

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	@Column(name = "ModifiedOn")
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime modifiedOn;

	public LocalDateTime getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(LocalDateTime modifiedOn) {
		this.modifiedOn = modifiedOn;
	}
	
	@Column(name = "ModifiedBy")
	private String modifiedBy;

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	
	@Transient
	private String userName;
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		try {
			// return new ObjectMapper().writeValueAsString(this);
			return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this);
		} catch (JsonProcessingException e) {
			return ReflectionToStringBuilder.toString(this);
		}
	}
	
	public String toJsonString() {
		try {
			return new ObjectMapper().writeValueAsString(this);
		} catch (JsonProcessingException e) {
			return ReflectionToStringBuilder.toString(this);
		}
	}
}
