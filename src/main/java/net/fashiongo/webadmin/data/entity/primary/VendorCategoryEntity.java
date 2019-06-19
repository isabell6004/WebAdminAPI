package net.fashiongo.webadmin.data.entity.primary;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "VendorCategory")
public class VendorCategoryEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Column(name = "VendorCategoryID")
    private Integer vendorCategoryID;

    @Column(name = "WholeSalerID")
    private Integer wholeSalerID;

    @Column(name = "CategoryName")
    private String categoryName;

    @Column(name = "CategoryName2")
    private String categoryName2;

    @Column(name = "CategoryDescription")
    private String categoryDescription;

    @Column(name = "imgheightG")
    private Integer imgheightG;

    @Column(name = "imgheightS")
    private Integer imgheightS;

    @Column(name = "ListOrder")
    private int listOrder = 1;

    @Column(name = "Active")
    private boolean active = true;

    @Column(name = "CreatedOn")
    private LocalDateTime createdOn;

    @Column(name = "CreatedBy")
    private String createdBy;

    @Column(name = "ModifiedOn")
    private LocalDateTime modifiedOn;

    @Column(name = "ModifiedBy")
    private String modifiedBy;

    @Column(name = "DefaultBodySizeID")
    private Integer defaultBodySizeID;

    @Column(name = "DefaultPatternID")
    private Integer defaultPatternID;

    @Column(name = "DefaultStyleID")
    private Integer defaultStyleID;

    @Column(name = "DefaultLengthID")
    private Integer defaultLengthID;

    @Column(name = "DefaultFabricID")
    private Integer defaultFabricID;

    @Column(name = "DefaultInventoryStatusID")
    private Integer defaultInventoryStatusID;

    @Column(name = "DefaultMadeIn")
    private String defaultMadeIn;

    @Column(name = "DefaultFabricDescription")
    private String defaultFabricDescription;

    @Column(name = "DefaultSizeID")
    private Integer defaultSizeID;

    @Column(name = "DefaultPackID")
    private Integer defaultPackID;

    @Column(name = "DefaultLabelID")
    private Integer defaultLabelID;

    @Column(name = "CategoryID")
    private Integer categoryID;

    @Column(name = "ParentVendorCategoryID")
    private Integer parentVendorCategoryID;

    @Column(name = "ParentCategoryID")
    private Integer parentCategoryID;

    @Column(name = "ParentParentCategoryID")
    private Integer parentParentCategoryID;
}
