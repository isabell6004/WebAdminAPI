package net.fashiongo.webadmin.data.entity.primary;

import lombok.Getter;
import lombok.Setter;
import net.fashiongo.common.conversion.LocalDateTimeConverter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Entity
@Table(name = "vendor")
public class VendorEntity {
    @Id
    @Column(name = "vendor_id")
    private Long vendor_id;

    @Column(name = "guid")
    private String guid;

    @Column(name = "name")
    private String name;

    @Column(name = "dba_name")
    private String dbaName;

    @Column(name = "codename")
    private String codename;

    @Column(name = "dirname")
    private String dirname;

    @Column(name = "description")
    private String description;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "starting_date")
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime startingDate;

    @Column(name = "website")
    private String website;

    @Column(name = "business_category_info")
    private String businessCategoryInfo;

    @Setter
    @Column(name = "class_code")
    private Integer classCode;

    @Column(name = "type_code")
    private Integer typeCode;

    @Column(name = "source_code")
    private Integer sourceCode;

    @Column(name = "established_year")
    private Integer establishedYear;

    @Column(name = "contact_person")
    private String contactPerson;

    @Column(name = "is_virtual_vendor")
    private Boolean isVirtualVendor;

    @Column(name = "how_known_id")
    private Integer howKnownId;

    @Column(name = "how_known_note")
    private String howKnownNote;

    @Column(name = "created_on", updatable = false)
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime createdOn;

    @Column(name = "created_by", updatable = false)
    private String createdBy;

    @Column(name = "modified_on")
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime modifiedOn;

    @Column(name = "modified_by")
    private String modifiedBy;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "vendorEntity")
    private Set<VendorSettingEntity> vendorSetting;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "vendorEntity")
    private Set<VendorContractHistoryEntity> vendorContractHistory;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "vendorEntity")
    private Set<VendorIndustryEntity> vendorIndustry;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "vendorEntity")
    private Set<VendorEmailEntity> vendorEmail;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "vendorEntity")
    private Set<VendorAddressEntity> vendorAddresses;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "vendorEntity")
    private Set<VendorBannerEntity> vendorBanner;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "vendorEntity")
    private Set<VendorAccountEntity> vendorAccount;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "vendorEntity")
    private Set<MapWaUserVendorEntity> mapWaUserVendorEntities;
}

