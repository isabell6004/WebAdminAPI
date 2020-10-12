package net.fashiongo.webadmin.data.model.vendor;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Builder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class VendorListCSVResponse {
    @JsonProperty(value = "ID")
    private Integer id;

    @JsonProperty(value = "Type")
    private String type;

    @JsonProperty(value = "Comapny Name")
    private String companyName;

    @JsonProperty(value = "Old Company Name")
    private String oldCompanyName;

    @JsonProperty(value = "Vendor Category")
    private String vendorCategory;

    @JsonProperty(value = "UserID")
    private String userID;

    @JsonProperty(value = "FirstName")
    private String firstName;

    @JsonProperty(value = "LastName")
    private String lastName;

    @JsonProperty(value = "EMail")
    private String email;

    @JsonProperty(value = "Show room Phone")
    private String showRoomPhone;

    @JsonProperty(value = "Show room Street")
    private String showRoomStreet;

    @JsonProperty(value = "Show room City")
    private String showRoomCity;

    @JsonProperty(value = "ShowroomSTATE")
    private String showRoomState;

    @JsonProperty(value = "Show room Zipcode")
    private String showRoomZipcode;

    @JsonProperty(value = "ShowroomCountry")
    private String showRoomCountry;

    @JsonProperty(value = "Factory Phone")
    private String factoryPhone;

    @JsonProperty(value = "Factory Street")
    private String factoryStreet;

    @JsonProperty(value = "Factory City")
    private String factoryCity;

    @JsonProperty(value = "FactorySTATE")
    private String factoryState;

    @JsonProperty(value = "FactoryCountry")
    private String factoryCountry;

    @JsonProperty(value = "Factory Zipcode")
    private String factoryZipcode;

    @JsonProperty(value = "Active")
    private String active;

    @JsonProperty(value = "Current Status")
    private String currentStatus;

    @JsonProperty(value = "Order Active on")
    private LocalDateTime orderActiveOn;

    @JsonProperty(value = "FashionGoExclusive")
    private Boolean fashionGoExclusive;


    public static VendorListCSVResponse create(VendorListCSVDto dto, VendorAddressSimpleDto billAddress, VendorAddressSimpleDto address) {
        if (dto == null) {
            throw new IllegalArgumentException("VendorListCSVDto must not be null");
        }
        VendorListCSVResponseBuilder builder = VendorListCSVResponse.builder().
                id(dto.getId().intValue())
                .type(dto.getType())
                .companyName(dto.getCompanyName())
                .oldCompanyName(dto.getOldCompanyName())
                .vendorCategory(dto.getVendorCategory())
                .userID(dto.getUserID())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .active(dto.getActive())
                .currentStatus(dto.getCurrentStatus())
                .orderActiveOn(dto.getOrderActiveOn())
                .fashionGoExclusive(dto.getFashionGoExclusive());
        if (Objects.nonNull(billAddress)) {
            builder = builder.showRoomPhone(billAddress.getPhone())
                    .showRoomStreet(billAddress.getStreetNo1() + " " + (billAddress.getStreetNo2() == null ? "" : billAddress.getStreetNo2()))
                    .showRoomCity(billAddress.getCity())
                    .showRoomState(billAddress.getState())
                    .showRoomZipcode(billAddress.getZipCode())
                    .showRoomCountry(billAddress.getCountryName());
        }
        if (Objects.nonNull(address)) {
            builder = builder.factoryPhone(address.getPhone())
                    .factoryStreet(address.getStreetNo1() + " " + (address.getStreetNo2() == null ? "" : address.getStreetNo2()))
                    .factoryCity(address.getCity())
                    .factoryState(address.getState())
                    .factoryZipcode(address.getZipCode())
                    .factoryCountry(address.getCountryName());
        }
        return builder.build();
    }

}
