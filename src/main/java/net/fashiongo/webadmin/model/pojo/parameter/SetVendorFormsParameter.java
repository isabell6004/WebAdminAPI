package net.fashiongo.webadmin.model.pojo.parameter;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author Reo
 *
 */
public class SetVendorFormsParameter {
	@JsonProperty("Type")
	private String type;
	
	@JsonProperty("FashionGoFormID")
	private Integer fashionGoFormID;
	
	@JsonProperty("FormName")
	private String formName;
	
	@JsonProperty("Memo")
	private String memo;
	
	@JsonProperty("Attachment")
	private String attachment;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

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
	
}
