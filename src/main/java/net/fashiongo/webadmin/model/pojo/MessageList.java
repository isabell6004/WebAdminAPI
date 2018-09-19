package net.fashiongo.webadmin.model.pojo;

public class MessageList {
	private Integer row;
	private Integer MapID;
	private Integer MessageID;
	private Integer ReferenceID;
	private String Title;
	private String Body;
	private Integer SenderTypeID;
	private Integer SenderID;
	private String SentOn;
	private String ReadOn;
	private String VendorCompanyName;
	private String RetailerCompanyName;
	private String ReadBy;
	private Integer MessageCategoryID;
	private String CategoryName;
	private String MessageGUID;
	private String AttachedFileName;
	private String AttachedFileName2;
	private String AttachedFileName3;
	private Integer WholeSalerID;
	private String VendorEmail;
	private String RetailerEmail;
	private Boolean IsDeletedBySender;
	private Boolean IsDeletedByRecipient;
	
	public Integer getRow() {
		return row;
	}
	public Integer getMapID() {
		return MapID;
	}
	public Integer getMessageID() {
		return MessageID;
	}
	public Integer getReferenceID() {
		return ReferenceID;
	}
	public String getTitle() {
		return Title;
	}
	public String getBody() {
		return Body;
	}
	public Integer getSenderTypeID() {
		return SenderTypeID;
	}
	public Integer getSenderID() {
		return SenderID;
	}
	public String getSentOn() {
		return SentOn;
	}
	public String getReadOn() {
		return ReadOn;
	}
	public String getVendorCompanyName() {
		return VendorCompanyName;
	}
	public String getRetailerCompanyName() {
		return RetailerCompanyName;
	}
	public String getReadBy() {
		return ReadBy;
	}
	public Integer getMessageCategoryID() {
		return MessageCategoryID;
	}
	public String getCategoryName() {
		return CategoryName;
	}
	public String getMessageGUID() {
		return MessageGUID;
	}
	public String getAttachedFileName() {
		return AttachedFileName;
	}
	public String getAttachedFileName2() {
		return AttachedFileName2;
	}
	public String getAttachedFileName3() {
		return AttachedFileName3;
	}
	public Integer getWholeSalerID() {
		return WholeSalerID;
	}
	public String getVendorEmail() {
		return VendorEmail;
	}
	public String getRetailerEmail() {
		return RetailerEmail;
	}
	public Boolean getIsDeletedBySender() {
		return IsDeletedBySender;
	}
	public Boolean getIsDeletedByRecipient() {
		return IsDeletedByRecipient;
	}
}