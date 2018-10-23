package net.fashiongo.webadmin.model.pojo;

public class VendorNews {
	private Integer row;
	private Integer newsID;
	private String recipient;
	private String newsTitle;
	private String fromDate;
	private String toDate;
	private Boolean active;
	private Integer wholeSalerID;
	public Integer getRow() {
		return row;
	}
	public Integer getNewsID() {
		return newsID;
	}
	public String getRecipient() {
		return recipient;
	}
	public String getNewsTitle() {
		return newsTitle;
	}
	public String getFromDate() {
		return fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public Boolean getActive() {
		return active;
	}
	public Integer getWholeSalerID() {
		return wholeSalerID;
	}
	public void setRow(Integer row) {
		this.row = row;
	}
	public void setNewsID(Integer newsID) {
		this.newsID = newsID;
	}
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	public void setWholeSalerID(Integer wholeSalerID) {
		this.wholeSalerID = wholeSalerID;
	}
	
	
}
