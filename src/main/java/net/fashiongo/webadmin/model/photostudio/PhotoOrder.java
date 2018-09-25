package net.fashiongo.webadmin.model.photostudio;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;

import net.fashiongo.common.conversion.LocalDateTimeConverter;
import net.fashiongo.common.dal.IPersistent;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Photo_Price")
public class PhotoOrder implements IPersistent, Serializable {
	
	private static final String DATE_PATTERN_YYYYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";
	private static final String EFFECTIVE_FROM_TIME = " 00:00:00";
	private static final String EFFECTIVE_TO_TIME = " 23:59:59";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "OrderID")
	private Integer orderID;
	public Integer getOrderID() {
		return orderID;
	}

	public void setOrderID(Integer orderID) {
		this.orderID = orderID;
	}

	@Column(name = "CartID")
	private Integer cartID;
	public Integer getCartID() {
		return cartID;
	}

	public void setCartID(Integer cartID) {
		this.cartID = cartID;
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

	@Column(name = "OrderNumber")
	private String orderNumber;
	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	@Column(name = "CategoryID")
	private Integer categoryID;
	public Integer getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(Integer categoryID) {
		this.categoryID = categoryID;
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

	@Column(name = "BookingID")
	private Integer bookingID;
	public Integer getBookingID() {
		return bookingID;
	}

	public void setBookingID(Integer bookingID) {
		this.bookingID = bookingID;
	}

	@Column(name = "OrderStatusID")
	private Integer orderStatusID;
	public Integer getOrderStatusID() {
		return orderStatusID;
	}

	public void setOrderStatusID(Integer orderStatusID) {
		this.orderStatusID = orderStatusID;
	}

	@Column(name = "TotalUnit")
	private BigDecimal totalUnit;
	public BigDecimal getTotalUnit() {
		return totalUnit;
	}

	public void setTotalUnit(BigDecimal totalUnit) {
		this.totalUnit = totalUnit;
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
		this.checkOutDate = StringUtils.isNotEmpty(checkOutDate) ? checkOutDate + EFFECTIVE_FROM_TIME : null;
		this.set_checkOutDate(StringUtils.isNotEmpty(this.checkOutDate) ? LocalDateTime.parse(this.checkOutDate, DateTimeFormatter.ofPattern(DATE_PATTERN_YYYYMMDDHHMMSS)) : null);
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
		this.photoshootDate = StringUtils.isNotEmpty(photoshootDate) ? photoshootDate + EFFECTIVE_FROM_TIME : null;
		this.set_photoshootDate(StringUtils.isNotEmpty(this.photoshootDate) ? LocalDateTime.parse(this.photoshootDate, DateTimeFormatter.ofPattern(DATE_PATTERN_YYYYMMDDHHMMSS)) : null);
	}

	@Column(name = "IsByStyle")
	private Boolean isByStyle;
	public Boolean getIsByStyle() {
		return isByStyle;
	}

	public void setIsByStyle(Boolean isByStyle) {
		this.isByStyle = isByStyle;
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
		this.dropOffDate = StringUtils.isNotEmpty(dropOffDate) ? dropOffDate + EFFECTIVE_FROM_TIME : null;
		this.set_dropOffDate(StringUtils.isNotEmpty(this.dropOffDate) ? LocalDateTime.parse(this.dropOffDate, DateTimeFormatter.ofPattern(DATE_PATTERN_YYYYMMDDHHMMSS)) : null);
	}

	@Column(name = "SpecialRequest")
	private String specialRequest;
	public String getSpecialRequest() {
		return specialRequest;
	}

	public void setSpecialRequest(String specialRequest) {
		this.specialRequest = specialRequest;
	}

	@Column(name = "InHouseNote")
	private String inHouseNote;
	public String getInHouseNote() {
		return inHouseNote;
	}

	public void setInHouseNote(String inHouseNote) {
		this.inHouseNote = inHouseNote;
	}

	@Column(name = "CancelTypeID")
	private Integer cancelTypeID;
	public Integer getCancelTypeID() {
		return cancelTypeID;
	}

	public void setCancelTypeID(Integer cancelTypeID) {
		this.cancelTypeID = cancelTypeID;
	}

	@Column(name = "CancelledBy")
	private String cancelledBy;
	public String getCancelledBy() {
		return cancelledBy;
	}

	public void setCancelledBy(String cancelledBy) {
		this.cancelledBy = cancelledBy;
	}

	@Column(name = "IsCancelledByVendor")
	private Boolean isCancelledByVendor;
	public Boolean getIsCancelledByVendor() {
		return isCancelledByVendor;
	}

	public void setIsCancelledByVendor(Boolean isCancelledByVendor) {
		this.isCancelledByVendor = isCancelledByVendor;
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
		this.cancelledOn = StringUtils.isNotEmpty(cancelledOn) ? cancelledOn + EFFECTIVE_FROM_TIME : null;
		this.set_cancelledOn(StringUtils.isNotEmpty(this.cancelledOn) ? LocalDateTime.parse(this.cancelledOn, DateTimeFormatter.ofPattern(DATE_PATTERN_YYYYMMDDHHMMSS)) : null);
	}

	@JsonIgnore
	@Column(name = "CreatedOn", updatable = false)
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime createdOnDate;

	public LocalDateTime getCreatedOnDate() {
		return createdOnDate;
	}

	public void setCreatedOnDate(LocalDateTime createdOnDate) {
		this.createdOnDate = createdOnDate;
	}

	@JsonIgnore
	@Transient
	private String createdOn;
	public String getCreatedOn() {
		return createdOnDate != null ? createdOnDate.toString() : null;
	}

	@JsonIgnore
	@Column(name = "CreatedBy")
	private String createdBy;
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@JsonIgnore
	@Column(name = "ModifiedOn")
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime modifiedOnDate;

	public LocalDateTime getModifiedOnDate() {
		return modifiedOnDate;
	}

	public void setModifiedOnDate(LocalDateTime modifiedOnDate) {
		this.modifiedOnDate = modifiedOnDate;
	}

	@JsonIgnore
	@Transient
	private String modifiedOn;
	public String getModifiedOn() {
		return modifiedOnDate != null ? modifiedOnDate.toString() : null;
	}

	@JsonIgnore
	@Column(name = "ModifiedBY")
	private String modifiedBY;
	public String getModifiedBY() {
		return modifiedBY;
	}

	public void setModifiedBY(String modifiedBY) {
		this.modifiedBY = modifiedBY;
	}

}
