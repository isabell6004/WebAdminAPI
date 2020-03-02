package net.fashiongo.webadmin.data.entity.primary;

import lombok.Builder;
import lombok.Getter;
import net.fashiongo.webadmin.model.buyer.BuyerAddressType;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Builder
@Entity
@Table(name = "buyer_address_history")
public class BuyerAddressHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "buyer_address_history_id")
    private Long id;

    @Column(name = "buyer_address_id")
    private Long buyerAddressId;

    @Column(name = "buyer_id")
    private Long buyerId;

    @Column(name = "address_type_code")
    private Integer typeCode;

    @Column(name = "address_name")
    private String name;

    @Column(name = "attention")
    private String attention;

    @Column(name = "street_no1")
    private String streetNo1;

    @Column(name = "street_no2")
    private String streetNo2;

    @Column(name = "store_no")
    private String storeNo;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "zipcode")
    private String zipCode;

    @Column(name = "country_name")
    private String countryName;

    @Column(name = "phone")
    private String phone;

    @Column(name = "phone2")
    private String phone2;

    @Column(name = "phone3")
    private String phone3;

    @Column(name = "fax")
    private String fax;

    @Column(name = "is_default")
    private Boolean isDefault;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "is_commercial")
    private Boolean isCommercial;
    
    @Column(name = "created_on")
    private LocalDateTime createdOn;
    
    @Column(name = "created_by")
    private String createdBy;
    
    public static BuyerAddressHistoryEntity createForBillingAddress(RetailerEntity retailer, String username) {

        return builder()
                .buyerId(Long.valueOf(retailer.getRetailerID()))
                .typeCode(BuyerAddressType.BILLING.getValue())
                .streetNo1(retailer.getBillStreetNo())
                .streetNo2(retailer.getBillStreetNo2())
                .city(retailer.getBillCity())
                .state(retailer.getBillSTATE())
                .zipCode(retailer.getBillZipcode())
                .countryName(retailer.getBillCountry())
                .phone(retailer.getBillPhone())
                .fax(retailer.getBillFax())
                .isDefault(true)
                .isActive(true)
                .isCommercial(false)
                .createdOn(LocalDateTime.now())
                .createdBy(username)
                .build();
    }

    public static BuyerAddressHistoryEntity createForCompanyAddress(RetailerEntity retailer, String username) {

        return builder()
                .buyerId(Long.valueOf(retailer.getRetailerID()))
                .typeCode(BuyerAddressType.COMPANY.getValue())
                .streetNo1(retailer.getStreetNo())
                .streetNo2(retailer.getStreetNo2())
                .city(retailer.getCity())
                .state(retailer.getState())
                .zipCode(retailer.getZipcode())
                .countryName(retailer.getCountry())
                .phone(retailer.getPhone())
                .fax(retailer.getFax())
                .isDefault(true)
                .isActive(true)
                .isCommercial(false)
                .createdOn(LocalDateTime.now())
                .createdBy(username)
                .build();
    }

    public static BuyerAddressHistoryEntity createForShippingAddress(XShipAddressEntity shipAddress, String username) {

        return builder()
                .buyerAddressId(Long.valueOf(shipAddress.getShipAddID()))
                .buyerId(Long.valueOf(shipAddress.getCustID2()))
                .typeCode(BuyerAddressType.SHIPPING.getValue())
                .name(shipAddress.getShipName2())
                .attention(shipAddress.getShipAttention())
                .streetNo1(shipAddress.getShipAddress2())
                .streetNo2(shipAddress.getShipAddress2B())
                .storeNo(shipAddress.getStoreNo())
                .city(shipAddress.getShipCity2())
                .state(shipAddress.getShipState2())
                .zipCode(shipAddress.getShipZip2())
                .countryName(shipAddress.getShipCountry2())
                .phone(shipAddress.getShipPhone())
                .fax(shipAddress.getShipFax())
                .isDefault(shipAddress.isDefaultYN())
                .isActive(shipAddress.isActive())
                .isCommercial(shipAddress.getIsCommercialAddress())
                .createdOn(LocalDateTime.now())
                .createdBy(username)
                .build();
    }
}
