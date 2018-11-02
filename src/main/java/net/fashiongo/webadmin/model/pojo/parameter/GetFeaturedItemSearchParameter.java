package net.fashiongo.webadmin.model.pojo.parameter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
import net.fashiongo.webadmin.common.Utility;

public class GetFeaturedItemSearchParameter {
	@ApiModelProperty(required = true, example = "0")
	@JsonProperty("pagenum")
	private String pageNum;

	@ApiModelProperty(required = true, example = "0")
	@JsonProperty("pagesize")
	private String pageSize;

	@ApiModelProperty(required = true, example = "0")
	@JsonProperty("fgcat")
	private String fgCat;

	@ApiModelProperty(required = true, example = "0")
	@JsonProperty("ccid")
	private String collectionCategoryID;

	@ApiModelProperty(required = true, example = "0")
	@JsonProperty("vendorid")
	private String vendorID;

	@ApiModelProperty(required = true, example = "0")
	@JsonProperty("selectedcategoryid")
	private String selectedCategoryID;

	@ApiModelProperty(required = true, example = "0")
	@JsonProperty("searchitemtxt")
	private String searchItemText;

	@ApiModelProperty(required = true, example = "0")
	@JsonProperty("BodySizeIDs")
	private String bodySizeIDs;

	@ApiModelProperty(required = true, example = "0")
	@JsonProperty("PatternIDs")
	private String patternIDs;

	@ApiModelProperty(required = true, example = "0")
	@JsonProperty("LengthIDs")
	private String lengthIDs;

	@ApiModelProperty(required = true, example = "0")
	@JsonProperty("StyleIDs")
	private String styleIDs;

	@ApiModelProperty(required = true, example = "0")
	@JsonProperty("FabricIDs")
	private String fabricIDs;

	@ApiModelProperty(required = true, example = "0")
	@JsonProperty("ColorNames")
	private String colorNames;

	@ApiModelProperty(required = true, example = "0")
	@JsonProperty("SearchAndOr")
	private String searchAndOr;
	
	@ApiModelProperty(required = true, example = "0")
	@JsonProperty("Keyword")
	private String keyword;

	@ApiModelProperty(required = true, example = "0")
	@JsonProperty("VendorOrderBy")
	private String vendorOrderBy;

	@ApiModelProperty(required = true, example = "0")
	@JsonProperty("OrderBy")
	private String orderBy;

	@ApiModelProperty(required = true, example = "0")
	@JsonProperty("StyleNo")
	private String styleNo;

	@ApiModelProperty(required = true, example = "0")
	@JsonProperty("NeverUsed")
	private String neverUsed;

	@ApiModelProperty(required = true, example = "0")
	@JsonProperty("VendorDateFrom")
	private String vendorDateFrom;

	@ApiModelProperty(required = true, example = "0")
	@JsonProperty("VendorDateTo")
	private String vendorDateTo;

	@ApiModelProperty(required = true, example = "0")
	@JsonProperty("FromDate")
	private String fromDate;

	@ApiModelProperty(required = true, example = "0")
	@JsonProperty("ToDate")
	private String toDate;

	public Integer getPageNum() {
		Integer pn = pageNum.isEmpty() ? 1 : Integer.parseInt(pageNum);
		return pn;
	}

	public void setPageNum(String pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		Integer ps = pageSize.isEmpty() ? 10 : Integer.parseInt(pageSize);
		return ps;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	public String getFgCat() {
		return fgCat.isEmpty() ? null : fgCat;
	}

	public void setFgCat(String fgCat) {
		this.fgCat = fgCat;
	}

	public Integer getCollectionCategoryID() {
		Integer ccid = collectionCategoryID.isEmpty() ? 0 : Integer.parseInt(collectionCategoryID);
		return ccid;
	}

	public void setCollectionCategoryID(String collectionCategoryID) {
		this.collectionCategoryID = collectionCategoryID;
	}

	public String getVendorID() {
		return vendorID;
	}

	public void setVendorID(String vendorID) {
		this.vendorID = vendorID;
	}

	public String getSelectedCategoryID() {
		return selectedCategoryID;
	}

	public void setSelectedCategoryID(String selectedCategoryID) {
		this.selectedCategoryID = selectedCategoryID;
	}

	public String getSearchItemText() {
		return searchItemText.isEmpty() ? null : Utility.ReplaceString(searchItemText);
	}

	public void setSearchItemText(String searchItemText) {
		this.searchItemText = searchItemText;
	}

	public String getBodySizeIDs() {
		return bodySizeIDs.isEmpty() ? null : Utility.ReplaceString(bodySizeIDs);
	}

	public void setBodySizeIDs(String bodySizeIDs) {
		this.bodySizeIDs = bodySizeIDs;
	}

	public String getPatternIDs() {
		return patternIDs.isEmpty() ? null : Utility.ReplaceString(patternIDs);
	}

	public void setPatternIDs(String patternIDs) {
		this.patternIDs = patternIDs;
	}

	public String getLengthIDs() {
		return lengthIDs.isEmpty() ? null : Utility.ReplaceString(lengthIDs);
	}

	public void setLengthIDs(String lengthIDs) {
		this.lengthIDs = lengthIDs;
	}

	public String getStyleIDs() {
		return styleIDs.isEmpty() ? null : Utility.ReplaceString(styleIDs);
	}

	public void setStyleIDs(String styleIDs) {
		this.styleIDs = styleIDs;
	}

	public String getFabricIDs() {
		return fabricIDs.isEmpty() ? null : Utility.ReplaceString(fabricIDs);
	}

	public void setFabricIDs(String fabricIDs) {
		this.fabricIDs = fabricIDs;
	}

	public String getColorNames() {
		return colorNames.isEmpty() ? null : Utility.ReplaceString(colorNames);
	}

	public void setColorNames(String colorNames) {
		this.colorNames = colorNames;
	}

	public String getSearchAndOr() {
		return searchAndOr.isEmpty() ? null : Utility.ReplaceString(searchAndOr);
	}

	public void setSearchAndOr(String searchAndOr) {
		this.searchAndOr = searchAndOr;
	}

	public String getKeyword() {
		return keyword.isEmpty() ? null : Utility.ReplaceString(keyword);
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getVendorOrderBy() {
		return vendorOrderBy.isEmpty() ? null : Utility.ReplaceString(vendorOrderBy);
	}

	public void setVendorOrderBy(String vendorOrderBy) {
		this.vendorOrderBy = vendorOrderBy;
	}

	public String getOrderBy() {
		return orderBy.isEmpty() ? null : Utility.ReplaceString(orderBy);
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getStyleNo() {
		return styleNo.isEmpty() ? null : Utility.ReplaceString(styleNo);
	}

	public void setStyleNo(String styleNo) {
		this.styleNo = styleNo;
	}

	public String getNeverUsed() {
		return neverUsed.isEmpty() ? null : Utility.ReplaceString(neverUsed);
	}

	public void setNeverUsed(String neverUsed) {
		this.neverUsed = neverUsed;
	}

	public LocalDateTime getVendorDateFrom() {
		DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy, hh:mm:ss");
		return LocalDateTime.parse(vendorDateFrom, dtFormatter);
	}

	public void setVendorDateFrom(String vendorDateFrom) {
		this.vendorDateFrom = vendorDateFrom;
	}

	public LocalDateTime getVendorDateTo() {
		DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy, hh:mm:ss");
		return LocalDateTime.parse(vendorDateTo, dtFormatter);
	}

	public void setVendorDateTo(String vendorDateTo) {
		this.vendorDateTo = vendorDateTo;
	}

	public LocalDateTime getFromDate() {
		DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy, hh:mm:ss");
		return LocalDateTime.parse(fromDate, dtFormatter);
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public LocalDateTime getToDate() {
		DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy, hh:mm:ss");
		return LocalDateTime.parse(toDate, dtFormatter);
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

}
