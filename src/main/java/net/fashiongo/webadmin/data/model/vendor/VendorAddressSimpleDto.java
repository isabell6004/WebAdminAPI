package net.fashiongo.webadmin.data.model.vendor;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class VendorAddressSimpleDto {
    private String phone;
    private String streetNo1;
    private String streetNo2;
    private String city;
    private String state;
    private String zipCode;
    private String countryName;

    @Builder
    public VendorAddressSimpleDto(String phone, String streetNo1, String streetNo2, String city,
                                  String state, String zipCode, String countryName) {
        this.phone = phone;
        this.streetNo1 = streetNo1;
        this.streetNo2 = streetNo2;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.countryName = countryName;
    }
}
