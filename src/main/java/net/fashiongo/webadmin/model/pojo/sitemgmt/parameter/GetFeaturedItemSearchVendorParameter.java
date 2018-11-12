package net.fashiongo.webadmin.model.pojo.sitemgmt.parameter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
import net.fashiongo.webadmin.common.Utility;

/**
* @author Junghwan Lee
*/
public class GetFeaturedItemSearchVendorParameter {
	@ApiModelProperty(required = true, example = "1")
	@JsonProperty("pagenum")
	private String pageNum;

	@ApiModelProperty(required = true, example = "10")
	@JsonProperty("pagesize")
	private String pageSize;

	@ApiModelProperty(required = true, example = "")
	@JsonProperty("fgcat")
	private String fgCat;

	@ApiModelProperty(required = true, example = "1079")
	@JsonProperty("vendorid")
	private String vendorID;

	@ApiModelProperty(required = true, example = "")
	@JsonProperty("selectedcategoryid")
	private String selectedCategoryID;

	@ApiModelProperty(required = true, example = "")
	@JsonProperty("searchitemtxt")
	private String searchItemText;

	@ApiModelProperty(required = true, example = "")
	@JsonProperty("BodySizeIDs")
	private String bodySizeIDs;

	@ApiModelProperty(required = true, example = "")
	@JsonProperty("PatternIDs")
	private String patternIDs;

	@ApiModelProperty(required = true, example = " ")
	@JsonProperty("LengthIDs")
	private String lengthIDs;

	@ApiModelProperty(required = true, example = "")
	@JsonProperty("StyleIDs")
	private String styleIDs;

	@ApiModelProperty(required = true, example = "")
	@JsonProperty("FabricIDs")
	private String fabricIDs;

	@ApiModelProperty(required = true, example = "")
	@JsonProperty("ColorNames")
	private String colorNames;

	@ApiModelProperty(required = true, example = "And")
	@JsonProperty("SearchAndOr")
	private String searchAndOr;
	
	@ApiModelProperty(required = true, example = "")
	@JsonProperty("Keyword")
	private String keyword;

	@ApiModelProperty(required = true, example = "")
	@JsonProperty("StyleNo")
	private String styleNo;

	@ApiModelProperty(required = true, example = "")
	@JsonProperty("NeverUsed")
	private String neverUsed;

	@ApiModelProperty(required = true, example = "")
	@JsonProperty("FromDate")
	private String fromDate;

	@ApiModelProperty(required = true, example = "")
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
		return StringUtils.isEmpty(fgCat) ? null : fgCat;
	}

	public void setFgCat(String fgCat) {
		this.fgCat = fgCat;
	}

	public String getVendorID() {
		return StringUtils.isEmpty(vendorID) ? null : vendorID;
	}

	public void setVendorID(String vendorID) {
		this.vendorID = vendorID;
	}

	public String getSelectedCategoryID() {
		return StringUtils.isEmpty(selectedCategoryID) ? null : selectedCategoryID;
	}

	public void setSelectedCategoryID(String selectedCategoryID) {
		this.selectedCategoryID = selectedCategoryID;
	}

	public String getSearchItemText() {
		return StringUtils.isEmpty(searchItemText) ? null : Utility.ReplaceString(searchItemText);
	}

	public void setSearchItemText(String searchItemText) {
		this.searchItemText = searchItemText;
	}

	public String getBodySizeIDs() {
		return StringUtils.isEmpty(bodySizeIDs) ? null : Utility.ReplaceString(bodySizeIDs);
	}

	public void setBodySizeIDs(String bodySizeIDs) {
		this.bodySizeIDs = bodySizeIDs;
	}

	public String getPatternIDs() {
		return StringUtils.isEmpty(patternIDs) ? null : Utility.ReplaceString(patternIDs);
	}

	public void setPatternIDs(String patternIDs) {
		this.patternIDs = patternIDs;
	}

	public String getLengthIDs() {
		return StringUtils.isEmpty(lengthIDs) ? null : Utility.ReplaceString(lengthIDs);
	}

	public void setLengthIDs(String lengthIDs) {
		this.lengthIDs = lengthIDs;
	}

	public String getStyleIDs() {
		return StringUtils.isEmpty(styleIDs) ? null : Utility.ReplaceString(styleIDs);
	}

	public void setStyleIDs(String styleIDs) {
		this.styleIDs = styleIDs;
	}

	public String getFabricIDs() {
		return StringUtils.isEmpty(fabricIDs) ? null : Utility.ReplaceString(fabricIDs);
	}

	public void setFabricIDs(String fabricIDs) {
		this.fabricIDs = fabricIDs;
	}

	public String getColorNames() {
		return StringUtils.isEmpty(colorNames) ? null : Utility.ReplaceString(colorNames);
	}

	public void setColorNames(String colorNames) {
		this.colorNames = colorNames;
	}

	public String getSearchAndOr() {
		return StringUtils.isEmpty(searchAndOr) ? null : Utility.ReplaceString(searchAndOr);
	}

	public void setSearchAndOr(String searchAndOr) {
		this.searchAndOr = searchAndOr;
	}

	public String getKeyword() {
		return StringUtils.isEmpty(keyword) ? null : Utility.ReplaceString(keyword);
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getStyleNo() {
		return StringUtils.isEmpty(styleNo) ? null :  Utility.ReplaceString(styleNo);
	}

	public void setStyleNo(String styleNo) {
		this.styleNo = styleNo;
	}

	public String getNeverUsed() {
		return StringUtils.isEmpty(neverUsed) ? null : Utility.ReplaceString(neverUsed);
	}

	public void setNeverUsed(String neverUsed) {
		this.neverUsed = neverUsed;
	}

	public LocalDateTime getFromDate() {
		if (StringUtils.isEmpty(fromDate)) {
			return null;
		}
		
		DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy, hh:mm:ss");
		return LocalDateTime.parse(fromDate, dtFormatter);
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public LocalDateTime getToDate() {
		if (StringUtils.isEmpty(toDate)) {
			return null;
		}
		
		DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy, hh:mm:ss");
		return LocalDateTime.parse(toDate, dtFormatter);
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
}
