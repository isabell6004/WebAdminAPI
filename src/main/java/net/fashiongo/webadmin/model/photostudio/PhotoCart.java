package net.fashiongo.webadmin.model.photostudio;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Photo_Cart")
@NamedEntityGraph(name = "graph.PhotoCart.cartDetails",
        attributeNodes = {@NamedAttributeNode("cartDetails"), @NamedAttributeNode("category"),
                @NamedAttributeNode("discount"), @NamedAttributeNode("packageInfo"), @NamedAttributeNode("model")}
)
public class PhotoCart implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CartID")
    private Integer id;

    @Column(name = "WholeSalerID")
    private Integer wholeSalerId;

    @Column(name = "WholeSalerCompanyName")
    private String wholeSalerCompanyName;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CategoryID", referencedColumnName = "CategoryID", updatable = false, insertable = false)
    private PhotoCategory category;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PackageID", referencedColumnName = "PackageID", updatable = false, insertable = false)
    private PhotoPackage packageInfo;

    @Column(name = "ColorID")
    private Integer colorId;

    @Column(name = "IsByStyle")
    private Boolean isByStyle;

    @Transient
    private String photoStudioOrderType;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "DiscountID", referencedColumnName = "DiscountID", updatable = false, insertable = false)
    private PhotoDiscount discount;

    @Column(name = "DiscountAmount")
    private BigDecimal discountAmount;

    @Column(name = "PhotoCreditUsedAmount")
    private BigDecimal photoCreditUsedAmount;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ModelID", referencedColumnName = "ModelID", updatable = false, insertable = false)
    private PhotoModel model;

    @Column(name = "PhotoshootDate")
    private Date photoshootDate;

    @Column(name = "SpecialRequest")
    private String specialRequest;

    @Column(name = "IsDeleted")
    private Boolean isDeleted;

    @Column(name = "CartStatusStep")
    private Integer cartStatusStep;

    @Column(name = "CreatedOn")
    private Date createdOn;

    @Column(name = "DiscountCode")
    private String discountCode;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "CartID", referencedColumnName = "CartID")
    private List<PhotoCartDetail> cartDetails;

    public Integer getCartStatusStep() {
        return cartStatusStep;
    }

    public void setCartStatusStep(Integer cartStatusStep) {
        this.cartStatusStep = cartStatusStep;
    }

    public PhotoCategory getCategory() {
        return category;
    }

    public void setCategory(PhotoCategory category) {
        this.category = category;
    }

    public Integer getColorId() {
        return colorId;
    }

    public void setColorId(Integer colorId) {
        this.colorId = colorId;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getByStyle() {
        return isByStyle;
    }

    public void setByStyle(Boolean byStyle) {
        isByStyle = byStyle;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public PhotoDiscount getDiscount() {
        return discount;
    }

    public void setDiscount(PhotoDiscount discount) {
        this.discount = discount;
    }

    public PhotoModel getModel() {
        return model;
    }

    public void setModel(PhotoModel model) {
        this.model = model;
    }

    public PhotoPackage getPackageInfo() {
        return packageInfo;
    }

    public void setPackageInfo(PhotoPackage packageInfo) {
        this.packageInfo = packageInfo;
    }

    public BigDecimal getPhotoCreditUsedAmount() {
        return photoCreditUsedAmount;
    }

    public void setPhotoCreditUsedAmount(BigDecimal photoCreditUsedAmount) {
        this.photoCreditUsedAmount = photoCreditUsedAmount;
    }

    public Date getPhotoshootDate() {
        return photoshootDate;
    }

    public void setPhotoshootDate(Date photoshootDate) {
        this.photoshootDate = photoshootDate;
    }

    public String getSpecialRequest() {
        return specialRequest;
    }

    public void setSpecialRequest(String specialRequest) {
        this.specialRequest = specialRequest;
    }

    public Integer getWholeSalerId() {
        return wholeSalerId;
    }

    public void setWholeSalerId(Integer wholeSalerId) {
        this.wholeSalerId = wholeSalerId;
    }

    public List<PhotoCartDetail> getCartDetails() {
        return cartDetails;
    }

    public void setCartDetails(List<PhotoCartDetail> cartDetails) {
        this.cartDetails = cartDetails;
    }

    public String getWholeSalerCompanyName() {
        return wholeSalerCompanyName;
    }

    public void setWholeSalerCompanyName(String wholeSalerCompanyName) {
        this.wholeSalerCompanyName = wholeSalerCompanyName;
    }

    public String getPhotoStudioOrderType() {
        try {
            return PhotoStudioOrderType.valueOf(this.isByStyle).name();
        } catch (Throwable t) {
            return "";
        }
    }

    public enum PhotoStudioOrderType {
        FULL(0),
        STYLE(1),;

        private int value;

        private PhotoStudioOrderType(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }

        public static PhotoStudioOrderType valueOf(Boolean value) throws Exception {

            if (value == false) {
                return FULL;
            } else if (value == true) {
                return STYLE;
            } else {
                throw new Exception("can not find type, ");
            }
        }
    }
}
