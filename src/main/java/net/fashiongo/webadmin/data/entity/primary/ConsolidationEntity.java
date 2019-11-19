package net.fashiongo.webadmin.data.entity.primary;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Consolidation")
public class ConsolidationEntity {
    @Id @Column(name = "ConsolidationID") private Integer id;
    @Column(name = "TotalAmount", nullable = false) private BigDecimal totalAmount;
    @Column(name = "TotalQty", nullable = false) private Integer totalQty;
    @Column(name = "OrderCount", nullable = false) private Integer orderCount;
    @Column(name = "InhouseMemo") private String inhouseMemo;
    @Column(name = "StreetNo", length = 100, nullable = false) private String streetNo;
    @Column(name = "City", length = 100, nullable = false) private String city;
    @Column(name = "State", length = 50, nullable = false) private String state;
    @Column(name = "Zipcode", length = 50, nullable = false) private String zipcode;
    @Column(name = "Country", length = 50, nullable = false) private String country;
    @Column(name = "CountryID") private Integer countryId;
    @Column(name = "IsCommercialAddress") private Boolean isCommercialAddress;
    @Column(name = "ModifiedOn") private LocalDateTime modifiedOn;
    @Column(name = "ModifiedBy", length = 50) private String modifiedBy;
}
