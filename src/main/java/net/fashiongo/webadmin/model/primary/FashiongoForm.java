package net.fashiongo.webadmin.model.primary;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author Reo
 *
 */
@Entity
@Table(name = "FashionGo_Form")
public class FashiongoForm {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("FashionGoFormID")
	@Column(name = "FashionGoFormID")
	private Integer fashionGoFormID;
	
	@JsonProperty("FormName")
	@Column(name = "FormName")
	private String formName;
	
	@JsonProperty("Memo")
	@Column(name = "Memo")
	private String memo;
	
	@JsonProperty("Attachment")
	@Column(name = "Attachment")
	private String attachment;
	
	@JsonProperty("CreatedOn")
	@Column(name = "CreatedOn")
	private LocalDateTime createdOn;
	
	@JsonProperty("CreatedBy")
	@Column(name = "CreatedBy")
	private String createdBy;
	
	@JsonProperty("ModifiedOn")
	@Column(name = "ModifiedOn")
	private LocalDateTime modifiedOn;
	
	@JsonProperty("ModifiedBy")
	@Column(name = "ModifiedBy")
	private String modifiedBy;
	
	@Transient
	@JsonProperty("Cehckbox")
	@Column(name = "Cehckbox")
	private Integer cehckbox;

	public Integer getFashionGoFormID() {
		return fashionGoFormID;
	}

	public void setFashionGoFormID(Integer fashionGoFormID) {
		this.fashionGoFormID = fashionGoFormID;
	}

	public String getFormName() {
		return formName;
	}

	public void setFormName(String formName) {
		this.formName = formName;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(LocalDateTime modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Integer getCehckbox() {
		return cehckbox;
	}

	public void setCehckbox(Integer cehckbox) {
		this.cehckbox = cehckbox;
	}
	
	
}
