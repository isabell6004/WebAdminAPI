package net.fashiongo.webadmin.model.photostudio;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Photo_Order")
@Getter
@Setter
public class PhotoOrderEntity implements Serializable {
	
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

	@Column(name = "CategoryID", insertable=false, updatable=false)
	private Integer categoryID;

	@Column(name = "PackageID")
	private Integer packageID;

	@Column(name = "ColorID", insertable=false, updatable=false)
	private Integer colorID;

	@Column(name = "IsByStyle")
	private Boolean isByStyle;

	@Column(name = "BookingID", insertable=false, updatable=false)
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

	@Column(name = "DiscountID")
	private Integer discountID;

	@Column(name = "DiscountAmount")
	private BigDecimal discountAmount;

	@Column(name = "DiscountCode")
	private String discountCode;

	@Column(name = "AdditionalDiscountAmount")
	private BigDecimal additionalDiscountAmount;

	@Column(name = "PhotoCreditUsedAmount")
	private BigDecimal photoCreditUsedAmount;

	@Column(name = "CheckOutDate")
	private LocalDateTime _checkOutDate;

	@Column(name = "DropOffDate")
	private LocalDateTime _dropOffDate;

	@Column(name = "PrepDate")
	private LocalDateTime _prepDate;

	@Column(name = "PhotoshootDate")
	private LocalDateTime _photoshootDate;

	@Column(name = "RetouchDate")
	private LocalDateTime _retouchDate;

	@Column(name = "UploadDate")
	private LocalDateTime _uploadDate;

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

	@Column(name = "CancelledOn")
	private LocalDateTime _cancelledOn;

	@Column(name = "CreatedBy")
	private String createdBy;

	@Column(name = "ModifiedOn")
	private LocalDateTime modifiedOnDate;

	@Column(name = "ModifiedBY")
	private String modifiedBY;

	@Column(name = "PickupDate")
	private LocalDateTime _pickupDate;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "OrderID", referencedColumnName = "OrderID")
    private List<PhotoOrderDetail> orderDetails;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CategoryID", referencedColumnName = "CategoryID")
    private PhotoCategory photoCategory;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BookingID", referencedColumnName = "BookingID")
    private PhotoBooking photoBooking;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ColorID", referencedColumnName = "ColorID")
    private CodePhotoBackgroundColor codePhotoBackgroundColor;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CancelTypeID", referencedColumnName = "CancelTypeID", insertable = false, updatable = false)
    private PhotoCancellationFee photoCancellationFee;
}
