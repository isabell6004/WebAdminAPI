package net.fashiongo.webadmin.data.model.vendor;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class VendorListCSVDto {
    private Long id;
    private String type;
    private String companyName;
    private String oldCompanyName;
    private String vendorCategory;
    private String userID;
    private String firstName;
    private String lastName;
    private String email;
    private String active;
    private String currentStatus;
    private LocalDateTime orderActiveOn;
    private Boolean fashionGoExclusive;
    private String phone;
    private String street1;
    private String street2;
    private String city;
    private String state;
    private String zipcode;
    private String country;
    private Integer addressTypeCode;

    public VendorListCSVDto(Long id, String type, String companyName, String oldCompanyName, String vendorCategory,
                            String userID, String firstName, String lastName, String email, String active, String currentStatus,
                            String orderActiveOn, Boolean fashionGoExclusive, String phone, String street1, String street2, String city, String state, String zipcode, String country, Integer addressTypeCode) {
        this.id = id;
        this.type = type;
        this.companyName = companyName;
        this.oldCompanyName = oldCompanyName;
        this.vendorCategory = vendorCategory;
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.active = active;
        this.currentStatus = currentStatus;
        this.orderActiveOn = orderActiveOn == null ? null : LocalDateTime.parse(orderActiveOn);
        this.fashionGoExclusive = fashionGoExclusive;
        this.phone = phone;
        this.street1 = street1;
        this.street2 = street2;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.country = country;
        this.addressTypeCode = addressTypeCode;
    }

    public static VendorAddressSimpleDto createVendorAddressSimpleDto(VendorListCSVDto dto) {
        if (dto == null) {
            throw new IllegalArgumentException("VendorListCSVDto must not be null");
        }
        return VendorAddressSimpleDto.builder()
                .phone(dto.getPhone())
                .streetNo1(dto.getStreet1())
                .streetNo2(dto.getStreet2())
                .city(dto.getCity())
                .state(dto.getState())
                .zipCode(dto.getZipcode())
                .countryName(dto.getCountry())
                .build();
    }

}
