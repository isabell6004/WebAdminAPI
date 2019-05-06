package net.fashiongo.webadmin.model.photostudio;

import lombok.Getter;
import lombok.Setter;

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
@Getter
@Setter
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
