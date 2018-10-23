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
 *  SimplePhotoOrder: Order info for Order Detail page
 */

public class DetailPhotoOrder {
	
	private static final String DATE_PATTERN_YYYYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";
	private static final String DEFAULT_TIME = " 00:00:00";

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
		this.checkOutDate = StringUtils.isNotEmpty(checkOutDate) ? checkOutDate : null;
		this.set_photoshootDate(StringUtils.isNotEmpty(this.checkOutDate) ? LocalDateTime.parse(this.checkOutDate, DateTimeFormatter.ofPattern(DATE_PATTERN_YYYYMMDDHHMMSS)) : null);

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
		this.photoshootDate = StringUtils.isNotEmpty(photoshootDate) ? photoshootDate : null;
		this.set_photoshootDate(StringUtils.isNotEmpty(this.photoshootDate) ? LocalDateTime.parse(this.photoshootDate, DateTimeFormatter.ofPattern(DATE_PATTERN_YYYYMMDDHHMMSS)) : null);
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
		this.dropOffDate = StringUtils.isNotEmpty(dropOffDate) ? dropOffDate : null;
		this.set_dropOffDate(StringUtils.isNotEmpty(this.dropOffDate) ? LocalDateTime.parse(this.dropOffDate, DateTimeFormatter.ofPattern(DATE_PATTERN_YYYYMMDDHHMMSS)) : null);
	}
	
	@Id
	@Column(name = "OrderID")
	private Integer orderID;
	public Integer getOrderID() {
		return orderID;
	}

	public void setOrderID(Integer orderID) {
		this.orderID = orderID;
	}
	
	@Column(name = "PONumber")
	private String poNumber;
	public String getPoNumber() {
		return poNumber;
	}

	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
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

	@Column(name = "TotalAmount")
	private BigDecimal totalAmount;
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	@Column(name = "PhotoCreditUsedAmount")
	private BigDecimal photoCreditUsedAmount;
	public BigDecimal getPhotoCreditUsedAmount() {
		return photoCreditUsedAmount;
	}

	public void setPhotoCreditUsedAmount(BigDecimal photoCreditUsedAmount) {
		this.photoCreditUsedAmount = photoCreditUsedAmount;
	}
	
	@Column(name = "DiscountID")
	private Integer discountID;
	public Integer getDiscountID() {
		return discountID;
	}

	public void setDiscountID(Integer discountID) {
		this.discountID = discountID;
	}
	
	@Column(name = "DiscountAmount")
	private BigDecimal discountAmount;
	public BigDecimal getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(BigDecimal discountAmount) {
		this.discountAmount = discountAmount;
	}
	
	@Column(name = "DiscountName")
	private String discountName;
	public String getDiscountName() {
		return discountName;
	}

	public void setDiscountName(String discountName) {
		this.discountName = discountName;
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
	
	@Column(name = "IsByStyle")
	private Boolean isByStyle;
	public Boolean getIsByStyle() {
		return isByStyle;
	}

	public void setIsByStyle(Boolean isByStyle) {
		this.isByStyle = isByStyle;
	}
	
	@Column(name = "OrderStatusID")
	private Integer orderStatusID;
	public Integer getOrderStatusID() {
		return orderStatusID;
	}

	public void setOrderStatusID(Integer orderStatusID) {
		this.orderStatusID = orderStatusID;
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
	
	@Column(name = "CancelTypeID")
	private Integer cancelTypeID;
	public Integer getCancelTypeID() {
		return cancelTypeID;
	}

	public void setCancelTypeID(Integer cancelTypeID) {
		this.cancelTypeID = cancelTypeID;
	}
	
	@Column(name = "CancellationFee")
	private BigDecimal cancellationFee;
	public BigDecimal getCancellationFee() {
		return cancellationFee;
	}

	public void setCancellationFee(BigDecimal cancellationFee) {
		this.cancellationFee = cancellationFee;
	}

	@Column(name = "CancelledByUserName")
	private String cancelledByUserName;
	public String getCancelledByUserName() {
		return cancelledByUserName;
	}

	public void setCancelledByUserName(String cancelledByUserName) {
		this.cancelledByUserName = cancelledByUserName;
	}

	@Column(name = "IsCancelled")
	private Integer isCancelled;
	public Integer getIsCancelled() {
		return isCancelled;
	}

	public void setIsCancelled(Integer isCancelled) {
		this.isCancelled = isCancelled;
	}

	@Column(name = "CancelNote")
	private String cancelNote;
	public String getCancelNote() {
		return cancelNote;
	}

	public void setCancelNote(String cancelNote) {
		this.cancelNote = cancelNote;
	}

	@JsonIgnore
	@Column(name = "CancelledOn")
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime _cancelledOn;
	public LocalDateTime get_cancelledOn() {
		return _cancelledOn;
	}

	public void set_cancelledOn(LocalDateTime _cancelledOn) {
		this._cancelledOn = _cancelledOn;
	}

	@Transient
	private String cancelledOn;
	public String getCancelledOn() {
		return _cancelledOn != null ? _cancelledOn.format(DateTimeFormatter.ofPattern(DATE_PATTERN_YYYYMMDDHHMMSS)) : null;
	}

	public void setCancelledOn(String cancelledOn) {
		this.cancelledOn = StringUtils.isNotEmpty(cancelledOn) ? cancelledOn + DEFAULT_TIME : null;
		this.set_cancelledOn(StringUtils.isNotEmpty(this.cancelledOn) ? LocalDateTime.parse(this.cancelledOn, DateTimeFormatter.ofPattern(DATE_PATTERN_YYYYMMDDHHMMSS)) : null);
	}
	
	
}
