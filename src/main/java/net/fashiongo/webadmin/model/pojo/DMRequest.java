package net.fashiongo.webadmin.model.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DMRequest implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private BigInteger row;
	@JsonProperty("CatalogSendRequestID")
	private Integer catalogSendRequestID;
	@JsonProperty("CatalogSendQueueID")
	private Integer catalogSendQueueID;
	@JsonProperty("FGCatalogID")
	private Integer fGCatalogID;
	@JsonProperty("CatalogSortNo")
	private Integer catalogSortNo;
	@JsonProperty("CreatedOn")
	private LocalDateTime createdOn;
	@JsonProperty("ModifiedOn")
	private LocalDateTime modifiedOn;
	@JsonProperty("Active")
	private Boolean active;
	@JsonProperty("WholeSalerID")
	private Integer wholeSalerID;
	@JsonProperty("CompanyTypeCD")
	private String companyTypeCD;
	@JsonProperty("CompanyName")
	private String companyName;
	@JsonProperty("SentOn")
	private LocalDateTime sentOn;
	@JsonProperty("CatalogID")
	private Integer catalogID;
	@JsonProperty("UrlPath")
	private String urlPath;
	@JsonProperty("DirName")
	private String dirName;
	@JsonProperty("C1")
	private String c1;
	@JsonProperty("C2")
	private String c2;
	@JsonProperty("C3")
	private String c3;
	@JsonProperty("C4")
	private String c4;
	@JsonProperty("TotalCount")
	private Integer totalCount;
	
	public BigInteger getRow() {
		return row;
	}
	public void setRow(BigInteger row) {
		this.row = row;
	}
	public Integer getCatalogSendRequestID() {
		return catalogSendRequestID;
	}
	public void setCatalogSendRequestID(Integer catalogSendRequestID) {
		this.catalogSendRequestID = catalogSendRequestID;
	}
	public Integer getCatalogSendQueueID() {
		return catalogSendQueueID;
	}
	public void setCatalogSendQueueID(Integer catalogSendQueueID) {
		this.catalogSendQueueID = catalogSendQueueID;
	}
	public Integer getfGCatalogID() {
		return fGCatalogID;
	}
	public void setfGCatalogID(Integer fGCatalogID) {
		this.fGCatalogID = fGCatalogID;
	}
	public Integer getCatalogSortNo() {
		return catalogSortNo;
	}
	public void setCatalogSortNo(Integer catalogSortNo) {
		this.catalogSortNo = catalogSortNo;
	}
	public LocalDateTime getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}
	public LocalDateTime getModifiedOn() {
		return modifiedOn;
	}
	public void setModifiedOn(LocalDateTime modifiedOn) {
		this.modifiedOn = modifiedOn;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	public Integer getWholeSalerID() {
		return wholeSalerID;
	}
	public void setWholeSalerID(Integer wholeSalerID) {
		this.wholeSalerID = wholeSalerID;
	}
	public String getCompanyTypeCD() {
		return companyTypeCD;
	}
	public void setCompanyTypeCD(String companyTypeCD) {
		this.companyTypeCD = companyTypeCD;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public LocalDateTime getSentOn() {
		return sentOn;
	}
	public void setSentOn(LocalDateTime sentOn) {
		this.sentOn = sentOn;
	}
	public Integer getCatalogID() {
		return catalogID;
	}
	public void setCatalogID(Integer catalogID) {
		this.catalogID = catalogID;
	}
	public String getUrlPath() {
		return urlPath;
	}
	public void setUrlPath(String urlPath) {
		this.urlPath = urlPath;
	}
	public String getDirName() {
		return dirName;
	}
	public void setDirName(String dirName) {
		this.dirName = dirName;
	}
	public String getC1() {
		return c1;
	}
	public void setC1(String c1) {
		this.c1 = c1;
	}
	public String getC2() {
		return c2;
	}
	public void setC2(String c2) {
		this.c2 = c2;
	}
	public String getC3() {
		return c3;
	}
	public void setC3(String c3) {
		this.c3 = c3;
	}
	public String getC4() {
		return c4;
	}
	public void setC4(String c4) {
		this.c4 = c4;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
}
