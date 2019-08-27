package net.fashiongo.webadmin.data.model.sitemgmt;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;


@Getter
public class TodayDealDetail {

	@JsonProperty("TodayDealID")
	private Integer todayDealID;
	@JsonProperty("Title")
	private String title;
	@JsonProperty("ProductID")
	private Integer productID;
	@JsonProperty("FromDate")
	private LocalDateTime fromDate;
	@JsonProperty("ToDate")
	private LocalDateTime toDate;
	@JsonProperty("TodayDealPrice")
	private BigDecimal todayDealPrice;
	@JsonProperty("Active")
	private Boolean active;
	@JsonProperty("AppliedOn")
	private LocalDateTime appliedOn;
	@JsonProperty("ApprovedOn")
	private LocalDateTime approvedOn;
	@JsonProperty("UnitPrice")
	private BigDecimal unitPrice;
	@JsonProperty("ProductName")
	private String productName;
	@JsonProperty("CompanyName")
	private String companyName;
	@JsonProperty("Status")
	private String status;
	@JsonProperty("CompanyTypeID")
	private Integer companyTypeID;
	@JsonProperty("CompanyTypeCode")
	private String companyTypeCode;
	@JsonProperty("CategoryID")
	private Integer categoryID;
	@JsonProperty("OrgCategoryID")
	private Integer orgCategoryID;
	@JsonProperty("CategoryName")
	private String categoryName;
	@JsonProperty("WholeSalerID")
	private Integer wholeSalerID;

	public TodayDealDetail(Integer todayDealID, String title, Integer productID, Timestamp fromDate, Timestamp toDate, BigDecimal todayDealPrice, Boolean active, Timestamp appliedOn, Timestamp approvedOn, BigDecimal unitPrice, String productName, String companyName, String status, Integer companyTypeID, String companyTypeCode, Integer categoryID, Integer orgCategoryID, String categoryName, Integer wholeSalerID) {
		this.todayDealID = todayDealID;
		this.title = title;
		this.productID = productID;
		this.fromDate = fromDate.toLocalDateTime();
		this.toDate = toDate.toLocalDateTime();
		this.todayDealPrice = todayDealPrice;
		this.active = active;
		this.appliedOn = appliedOn.toLocalDateTime();
		this.approvedOn = approvedOn.toLocalDateTime();
		this.unitPrice = unitPrice;
		this.productName = productName;
		this.companyName = companyName;
		this.status = status;
		this.companyTypeID = companyTypeID;
		this.companyTypeCode = companyTypeCode;
		this.categoryID = categoryID;
		this.orgCategoryID = orgCategoryID;
		this.categoryName = categoryName;
		this.wholeSalerID = wholeSalerID;
	}
}
