package net.fashiongo.webadmin.model.pojo.ad.parameter;

import java.time.LocalDateTime;

import javax.persistence.Column;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
* @author Jiwon Kim
*/
public class GetCategoryAdItemSearchParameter {
    
	@ApiModelProperty(required = false, example="1")
	@JsonProperty("pagenum")
	private Integer pagenum;
    @ApiModelProperty(required = false, example="10")
	@JsonProperty("pagesize")
	private Integer pagesize;
    @ApiModelProperty(required = false, example="1")
	@JsonProperty("CategoryIDs")
	private String categoryIDs;
    @ApiModelProperty(required = false, example="12")
	@JsonProperty("CollectionCategoryID")
	private Integer collectionCategoryID;
    @ApiModelProperty(required = false, example="")
	@JsonProperty("VendorID")
	private String vendorID;
    @ApiModelProperty(required = false, example="")
	@JsonProperty("SelectedCategoryID")
	private String selectedCategoryID;
    @ApiModelProperty(required = false, example="")
	@JsonProperty("Filter")
	private String filter;
    @ApiModelProperty(required = false, example="")
	@JsonProperty("BodySizeIDs")
	private String bodySizeIDs;
    @ApiModelProperty(required = false, example="")
	@JsonProperty("PatternIDs")
	private String patternIDs;
    @ApiModelProperty(required = false, example="")
	@JsonProperty("LengthIDs")
	private String lengthIDs;
    @ApiModelProperty(required = false, example="")
	@JsonProperty("StyleIDs")
	private String styleIDs;
    @ApiModelProperty(required = false, example="")
	@JsonProperty("FabricIDs")
	private String fabricIDs;
    @ApiModelProperty(required = false, example="")
	@JsonProperty("ColorNames")
	private String colorNames;
    @ApiModelProperty(required = false, example="And")
	@JsonProperty("SearchAndOr")
	private String searchAndOr;
    @ApiModelProperty(required = false, example="")
	@JsonProperty("Keyword")
	private String keyword;
    @ApiModelProperty(required = false, example="")
	@JsonProperty("VendorOrderBy")
	private String vendorOrderBy;
    @ApiModelProperty(required = false, example="")
	@JsonProperty("OrderBy")
	private String orderBy;
    @ApiModelProperty(required = false, example="")
	@JsonProperty("StyleNo")
	private String styleNo;
    @ApiModelProperty(required = false, example="")
	@JsonProperty("NeverUsed")
	private String neverUsed;
	@JsonProperty("VendorDateFrom")
	@Column(name = "VendorDateFrom")
	private String vendorDateFrom;
	@JsonProperty("VendorDateTo")
	@Column(name = "VendorDateTo")
	private String vendorDateTo;
	@JsonProperty("FromDate")
	@Column(name = "FromDate")
	private String fromDate;
	@JsonProperty("ToDate")
	@Column(name = "ToDate")
	private String toDate;
    
    
    
    
    
    
	
	public Integer getPagenum() {
		return pagenum;
	}
	public void setPagenum(Integer pagenum) {
		this.pagenum = pagenum;
	}
	public Integer getPagesize() {
		return pagesize;
	}
	public void setPagesize(Integer pagesize) {
		this.pagesize = pagesize;
	}
	public String getCategoryIDs() {
		return categoryIDs;
	}
	public void setCategoryIDs(String categoryIDs) {
		this.categoryIDs = categoryIDs;
	}
	public Integer getCollectionCategoryID() {
		return collectionCategoryID;
	}
	public void setCollectionCategoryID(Integer collectionCategoryID) {
		this.collectionCategoryID = collectionCategoryID;
	}
	public String getVendorID() {
		return StringUtils.isEmpty(vendorID) ? null : vendorID;
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
	public String getFilter() {
		return StringUtils.isEmpty(filter) ? null : filter;
	}
	public void setFilter(String filter) {
		this.filter = filter;
	}
	public String getBodySizeIDs() {
		return StringUtils.isEmpty(bodySizeIDs) ? null : bodySizeIDs;
	}
	public void setBodySizeIDs(String bodySizeIDs) {
		this.bodySizeIDs = bodySizeIDs;
	}
	public String getPatternIDs() {
		return StringUtils.isEmpty(patternIDs) ? null : patternIDs;
	}
	public void setPatternIDs(String patternIDs) {
		this.patternIDs = patternIDs;
	}
	public String getLengthIDs() {
		return StringUtils.isEmpty(lengthIDs) ? null : lengthIDs;
	}
	public void setLengthIDs(String lengthIDs) {
		this.lengthIDs = lengthIDs;
	}
	public String getStyleIDs() {
		return StringUtils.isEmpty(styleIDs) ? null : styleIDs;
	}
	public void setStyleIDs(String styleIDs) {
		this.styleIDs = styleIDs;
	}
	public String getFabricIDs() {
		return StringUtils.isEmpty(fabricIDs) ? null : fabricIDs;
	}
	public void setFabricIDs(String fabricIDs) {
		this.fabricIDs = fabricIDs;
	}
	public String getColorNames() {
		return StringUtils.isEmpty(colorNames) ? null : colorNames;
	}
	public void setColorNames(String colorNames) {
		this.colorNames = colorNames;
	}
	public String getSearchAndOr() {
		return searchAndOr;
	}
	public void setSearchAndOr(String searchAndOr) {
		this.searchAndOr = searchAndOr;
	}
	public String getKeyword() {
		return StringUtils.isEmpty(keyword) ? null : keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getVendorOrderBy() {
		return StringUtils.isEmpty(vendorOrderBy) ? null : vendorOrderBy;
	}
	public void setVendorOrderBy(String vendorOrderBy) {
		this.vendorOrderBy = vendorOrderBy;
	}
	public String getOrderBy() {
		return StringUtils.isEmpty(orderBy) ? null : orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	public String getStyleNo() {
		return styleNo;
	}
	public void setStyleNo(String styleNo) {
		this.styleNo = styleNo;
	}
	public String getNeverUsed() {
		return StringUtils.isEmpty(neverUsed) ? null : neverUsed;
	}
	public void setNeverUsed(String neverUsed) {
		this.neverUsed = neverUsed;
	}
	public String getVendorDateFrom() {
		return StringUtils.isEmpty(vendorDateFrom) ? null : vendorDateFrom;
	}
	public void setVendorDateFrom(String vendorDateFrom) {
		this.vendorDateFrom = vendorDateFrom;
	}
	public String getVendorDateTo() {
		return StringUtils.isEmpty(vendorDateTo) ? null : vendorDateTo;
	}
	public void setVendorDateTo(String vendorDateTo) {
		this.vendorDateTo = vendorDateTo;
	}
	public String getFromDate() {
		return StringUtils.isEmpty(fromDate) ? null : fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return StringUtils.isEmpty(toDate) ? null : toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

}