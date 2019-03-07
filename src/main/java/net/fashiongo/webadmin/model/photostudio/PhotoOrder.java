package net.fashiongo.webadmin.model.photostudio;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.*;
import javax.persistence.criteria.Fetch;

import lombok.Getter;
import lombok.Setter;
import net.fashiongo.common.conversion.LocalDateTimeConverter;
import net.fashiongo.common.dal.IPersistent;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "Photo_Order")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class PhotoOrder implements IPersistent, Serializable {
	
	private static final String DATE_PATTERN_YYYYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";
	private static final String DATE_PATTERN_YYYYMMDD = "yyyy-MM-dd";
	private static final String DEFAULT_TIME = " 18:00:00";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "OrderID")
	private Integer orderID;

	@Column(name = "CartID")
	private Integer cartID;

	@Column(name = "WholeSalerID")
    private Integer wholeSalerID;

	@Column(name = "WholeSalerCompanyName")
	private String wholeSalerCompanyName;

	@Column(name = "PONumber")
	private String poNumber;

	@Column(name = "CategoryID", insertable = false, updatable = false)
    private Integer categoryID;

	@Column(name = "PackageID", insertable = false, updatable = false)
    private Integer packageID;

	@Column(name = "ColorID")
    private Integer colorID;

	@Column(name = "IsByStyle")
	private Boolean isByStyle;

	@Column(name = "BookingID", insertable = false, updatable = false)
	private Integer bookingID;

	@Column(name = "OrderStatusID")
	private Integer orderStatusID;

	@Column(name = "TotalUnit")
	private BigDecimal totalUnit;

	@Column(name = "TotalQty")
	private Integer totalQty;

	@Column(name = "SubtotalAmount")
	private BigDecimal subtotalAmount;

	@Column(name = "TotalAmount", insertable=false, updatable=false)
	private BigDecimal totalAmount;

	@Column(name = "DiscountID", insertable = false, updatable = false)
	private Integer discountID;

	@Column(name = "DiscountAmount")
	private BigDecimal discountAmount;

	@Column(name = "DiscountCode")
	private String discountCode;

	@Column(name = "AdditionalDiscountAmount")
	private BigDecimal additionalDiscountAmount;

	@Column(name = "PhotoCreditUsedAmount")
	private BigDecimal photoCreditUsedAmount;

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
		return _checkOutDate != null ? _checkOutDate.format(DateTimeFormatter.ofPattern(DATE_PATTERN_YYYYMMDD)) : null;
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
		return _dropOffDate != null ? _dropOffDate.format(DateTimeFormatter.ofPattern(DATE_PATTERN_YYYYMMDD)) : null;
	}

	@JsonIgnore
	@Column(name = "PrepDate")
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime _prepDate;
	public LocalDateTime get_prepDate() {
		return _prepDate;
	}

	public void set_prepDate(LocalDateTime _prepDate) {
		this._prepDate = _prepDate;
	}

	@Transient
	private String prepDate;
	public String getPrepDate() {
		return _prepDate != null ? _prepDate.format(DateTimeFormatter.ofPattern(DATE_PATTERN_YYYYMMDD)) : null;
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
		return _photoshootDate != null ? _photoshootDate.format(DateTimeFormatter.ofPattern(DATE_PATTERN_YYYYMMDD)) : null;
	}
	
	public String getPhotoshootDateTime() {
		return _photoshootDate != null ? _photoshootDate.format(DateTimeFormatter.ofPattern(DATE_PATTERN_YYYYMMDDHHMMSS)) : null;
	}

	public void setPhotoshootDate(String photoshootDate) {
		this.photoshootDate = StringUtils.isNotEmpty(photoshootDate) ? photoshootDate + DEFAULT_TIME : null;
		this.set_photoshootDate(StringUtils.isNotEmpty(this.photoshootDate) ? LocalDateTime.parse(this.photoshootDate, DateTimeFormatter.ofPattern(DATE_PATTERN_YYYYMMDDHHMMSS)) : null);
	}

	@JsonIgnore
	@Column(name = "RetouchDate")
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime _retouchDate;
	public LocalDateTime get_retouchDate() {
		return _retouchDate;
	}

	public void set_retouchDate(LocalDateTime _retouchDate) {
		this._retouchDate = _retouchDate;
	}

	@Transient
	private String retouchDate;
	public String getRetouchDate() {
		return _retouchDate != null ? _retouchDate.format(DateTimeFormatter.ofPattern(DATE_PATTERN_YYYYMMDD)) : null;
	}

	@JsonIgnore
	@Column(name = "UploadDate")
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime _uploadDate;
	public LocalDateTime get_uploadDate() {
		return _uploadDate;
	}

	public void set_uploadDate(LocalDateTime _uploadDate) {
		this._uploadDate = _uploadDate;
	}

	@Transient
	private String uploadDate;
	public String getUploadDate() {
		return _uploadDate != null ? _uploadDate.format(DateTimeFormatter.ofPattern(DATE_PATTERN_YYYYMMDD)) : null;
	}

	@Column(name = "SpecialRequest")
	private String specialRequest;

	@Column(name = "InHouseNote")
	private String inHouseNote;

	@Column(name = "CancelTypeID")
	private Integer cancelTypeID;

	@Column(name = "CancellationFee")
	private BigDecimal cancellationFee;

	@Column(name = "CancellationFeeRate")
	private BigDecimal cancellationFeeRate;

	@Column(name = "CancelledByUserName")
	private String cancelledByUserName;

	@Column(name = "IsCancelledBy")
	private Integer isCancelledBy;

	@Column(name = "CancelNote")
	private String cancelNote;

    @Column(name = "IsSocialMediaPromotionAgree")
    private Boolean isSocialMediaPromotionAgree;

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
		return _cancelledOn != null ? _cancelledOn.format(DateTimeFormatter.ofPattern(DATE_PATTERN_YYYYMMDD)) : null;
	}

	@JsonIgnore
	@Column(name = "CreatedBy")
	private String createdBy;

	@JsonIgnore
	@Column(name = "ModifiedOn")
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime modifiedOnDate;

    @JsonIgnore
    @Column(name = "PickupDate")
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime pickupDate;

    @JsonIgnore
	@Transient
	private String modifiedOn;
	public String getModifiedOn() {
		return modifiedOnDate != null ? modifiedOnDate.toString() : null;
	}

	@Column(name = "ModifiedBY")
	private String modifiedBY;

	@Transient
	private Integer modelID;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "OrderID", referencedColumnName = "OrderID")
    private List<PhotoOrderDetail> orderDetails;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CategoryID", referencedColumnName = "CategoryID")
    private PhotoCategory photoCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DiscountID", referencedColumnName = "DiscountID")
    private PhotoDiscount photoDiscount;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BookingID", referencedColumnName = "BookingID")
    private PhotoBooking photoBooking;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PackageID", referencedColumnName = "PackageID")
    private PhotoPackage photoPackage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ColorID", referencedColumnName = "ColorID")
    private CodePhotoBackgroundColor codePhotoBackgroundColor;


}
