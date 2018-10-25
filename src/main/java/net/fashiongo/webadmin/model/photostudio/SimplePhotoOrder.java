/**
 * 
 */
package net.fashiongo.webadmin.model.photostudio;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Id;
import javax.persistence.Transient;

import net.fashiongo.common.conversion.LocalDateTimeConverter;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author XiaoChuan.Gao
 *  SimplePhotoOrder: Order info for Order Listing page
 */

public class SimplePhotoOrder {
	
	private static final String DATE_PATTERN_YYYYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";
	private static final String DEFAULT_TIME = " 00:00:00";

	@Id
	@Column(name = "OrderID")
	private Integer orderID;
	public Integer getOrderID() {
		return orderID;
	}

	public void setOrderID(Integer orderID) {
		this.orderID = orderID;
	}

	@JsonIgnore
	@Column(name = "PhotoshootDate")
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime _photoshootDate;
	public LocalDateTime get_photoshootDate() {
		return _photoshootDate;
	}

	public void set_photoshootDate(LocalDateTime _photoshootDate) {
		this._photoshootDate = _photoshootDate;
	}

	@Transient
	private String photoshootDate;
	public String getPhotoshootDate() {
		return _photoshootDate != null ? _photoshootDate.format(DateTimeFormatter.ofPattern(DATE_PATTERN_YYYYMMDDHHMMSS)) : null;
	}

	public void setPhotoshootDate(String photoshootDate) {
		this.photoshootDate = StringUtils.isNotEmpty(photoshootDate) ? photoshootDate + DEFAULT_TIME : null;
		this.set_photoshootDate(StringUtils.isNotEmpty(this.photoshootDate) ? LocalDateTime.parse(this.photoshootDate, DateTimeFormatter.ofPattern(DATE_PATTERN_YYYYMMDDHHMMSS)) : null);
	}
	
	@JsonIgnore
	@Column(name = "CheckOutDate")
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime _checkOutDate;
	public LocalDateTime get_checkOutDate() {
		return _checkOutDate;
	}

	public void set_checkOutDate(LocalDateTime _checkOutDate) {
		this._checkOutDate = _checkOutDate;
	}

	@Transient
	private String checkOutDate;
	public String getCheckOutDate() {
		return _checkOutDate != null ? _checkOutDate.format(DateTimeFormatter.ofPattern(DATE_PATTERN_YYYYMMDDHHMMSS)) : null;
	}

	public void setCheckOutDate(String checkOutDate) {
		this.checkOutDate = StringUtils.isNotEmpty(checkOutDate) ? checkOutDate + DEFAULT_TIME : null;
		this.set_photoshootDate(StringUtils.isNotEmpty(this.checkOutDate) ? LocalDateTime.parse(this.checkOutDate, DateTimeFormatter.ofPattern(DATE_PATTERN_YYYYMMDDHHMMSS)) : null);
	}
	
	@Column(name = "PONumber")
	private String poNumber;
	public String getPoNumber() {
		return poNumber;
	}

	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}

	@Column(name = "InHouseNote")
	private String inHouseNote;
	public String getInHouseNote() {
		return inHouseNote;
	}

	public void setInHouseNote(String inHouseNote) {
		this.inHouseNote = inHouseNote;
	}
	
	@Column(name = "SpecialRequest")
	private String specialRequest;
	public String getSpecialRequest() {
		return specialRequest;
	}

	public void setSpecialRequest(String specialRequest) {
		this.specialRequest = specialRequest;
	}

	@Column(name = "WholeSalerID")
	private Integer wholeSalerID;
	public Integer getWholeSalerID() {
		return wholeSalerID;
	}

	public void setWholeSalerID(Integer wholeSalerID) {
		this.wholeSalerID = wholeSalerID;
	}

	@Column(name = "WholeSalerCompanyName")
	private String wholeSalerCompanyName;
	public String getWholeSalerCompanyName() {
		return wholeSalerCompanyName;
	}

	public void setWholeSalerCompanyName(String wholeSalerCompanyName) {
		this.wholeSalerCompanyName = wholeSalerCompanyName;
	}
	
	@Column(name = "CategoryID")
	private Integer categoryID;
	public Integer getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(Integer categoryID) {
		this.categoryID = categoryID;
	}
	
	@Column(name = "CategoryName")
	private String categoryName;
	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	@Column(name = "PackageID")
	private Integer packageID;
	public Integer getPackageID() {
		return packageID;
	}

	public void setPackageID(Integer packageID) {
		this.packageID = packageID;
	}
	
	@Column(name = "PackageName")
	private String packageName;
	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	@Column(name = "TypeOfPhotoshoot")
	private String typeOfPhotoshoot;
	public String getTypeOfPhotoshoot() {
		return typeOfPhotoshoot;
	}

	public void setTypeOfPhotoshoot(String typeOfPhotoshoot) {
		this.typeOfPhotoshoot = typeOfPhotoshoot;
	}

	@Column(name = "TotalQty")
	private Integer totalQty;
	public Integer getTotalQty() {
		return totalQty;
	}

	public void setTotalQty(Integer totalQty) {
		this.totalQty = totalQty;
	}

	@JsonIgnore
	@Column(name = "DropOffDate")
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime _dropOffDate;
	public LocalDateTime get_dropOffDate() {
		return _dropOffDate;
	}

	public void set_dropOffDate(LocalDateTime _dropOffDate) {
		this._dropOffDate = _dropOffDate;
	}

	@Transient
	private String dropOffDate;
	public String getDropOffDate() {
		return _dropOffDate != null ? _dropOffDate.format(DateTimeFormatter.ofPattern(DATE_PATTERN_YYYYMMDDHHMMSS)) : null;
	}

	public void setDropOffDate(String dropOffDate) {
		this.dropOffDate = StringUtils.isNotEmpty(dropOffDate) ? dropOffDate + DEFAULT_TIME : null;
		this.set_dropOffDate(StringUtils.isNotEmpty(this.dropOffDate) ? LocalDateTime.parse(this.dropOffDate, DateTimeFormatter.ofPattern(DATE_PATTERN_YYYYMMDDHHMMSS)) : null);
	}

	@Column(name = "TotalAmount")
	private BigDecimal totalAmount;
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	@Column(name = "OrderStatusID")
	private Integer orderStatusID;
	public Integer getOrderStatusID() {
		return orderStatusID;
	}

	public void setOrderStatusID(Integer orderStatusID) {
		this.orderStatusID = orderStatusID;
	}
	
	@Column(name = "IsCancelledBy")
	private Integer isCancelledBy;
	public Integer getIsCancelledBy() {
		return isCancelledBy;
	}

	public void setIsCancelledBy(Integer isCancelledBy) {
		this.isCancelledBy = isCancelledBy;
	}

	@Column(name = "CancelNote")
	private String cancelNote;
	public String getCancelNote() {
		return cancelNote;
	}

	public void setCancelNote(String cancelNote) {
		this.cancelNote = cancelNote;
	}
	
	@Column(name = "IsDelayed")
	private Boolean isDelayed;
	public Boolean getIsDelayed() {
		return isDelayed;
	}

	public void setIsDelayed(Boolean isDelayed) {
		this.isDelayed = isDelayed;
	}
	
	@Column(name = "ModelID")
	private Integer modelID;
	public Integer getModelID() {
		return modelID;
	}
	public void setModelID(Integer modelID) {
		this.modelID = modelID;
	}
	
	@Column(name = "ModelName")
	private String modelName;
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	
	@Column(name = "TotalUnit")
	private BigDecimal totalUnit;
	public BigDecimal getTotalUnit() {
		return totalUnit;
	}

	public void setTotalUnit(BigDecimal totalUnit) {
		this.totalUnit = totalUnit;
	}
}
