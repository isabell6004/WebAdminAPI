package net.fashiongo.webadmin.model.pojo.parameter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
* @author Jiwon Kim
*/
public class SetCategoryAdItemParameter {
    
	
	@ApiModelProperty(required = false, example="0")
	@JsonProperty("SpotID")
	@Column(name = "SpotID")
	private String spotID;
	@ApiModelProperty(required = false, example="2018-10-01")
	@JsonProperty("FromDate")
	@Column(name = "FromDate")
	private String fromDate;
	@ApiModelProperty(required = false, example="0")
	@JsonProperty("CollectionCategoryID")
	@Column(name = "CollectionCategoryID")
	private String collectioncategoryid;
	@ApiModelProperty(required = false, example="0")
	@JsonProperty("ProductID")
	@Column(name = "ProductID")
	private String productID;
	@ApiModelProperty(required = false, example="0")
	@JsonProperty("WholesalerID")
	@Column(name = "WholesalerID")
	private String wholesalerID;
	
    
    
	public Integer getSpotID() {
		return StringUtils.isEmpty(spotID) ? 0 : Integer.parseInt(spotID);
	}
	public LocalDateTime getFromDate() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return StringUtils.isEmpty(fromDate) ? null : LocalDateTime.parse(fromDate+" 00:00:00",formatter);
	}
	public Integer getCollectioncategoryid() {
		return StringUtils.isEmpty(collectioncategoryid) ? 0 : Integer.parseInt(collectioncategoryid);
	}
	public Integer getProductID() {
		return StringUtils.isEmpty(productID) ? 0 : Integer.parseInt(productID);
	}
	public Integer getWholesalerID() {
		return StringUtils.isEmpty(wholesalerID) ? 0 : Integer.parseInt(wholesalerID);
	}
    

}