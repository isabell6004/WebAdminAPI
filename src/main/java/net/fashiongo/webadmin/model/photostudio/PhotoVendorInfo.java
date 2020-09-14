package net.fashiongo.webadmin.model.photostudio;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import net.fashiongo.webadmin.data.model.vendor.response.VendorResponse;

@Getter
public class PhotoVendorInfo {
    @JsonProperty("WholeSalerID")
    private Integer wholeSalerId;

    @JsonProperty("CompanyName")
    private String companyName;

    @JsonIgnore
    private Boolean active;

    @JsonIgnore
    private Boolean shopActive;

    @JsonIgnore
    private Boolean orderActive;

    @JsonIgnore
    private Integer vendorType;

    private String phone;

    public PhotoVendorInfo() {
    }

    @Builder
    public PhotoVendorInfo(Integer wholeSalerId, String companyName, Boolean active, Boolean shopActive, Boolean orderActive, Integer vendorType, String phone) {
        this.wholeSalerId = wholeSalerId;
        this.companyName = companyName;
        this.active = active;
        this.shopActive = shopActive;
        this.orderActive = orderActive;
        this.vendorType = vendorType;
        this.phone = phone;
    }

    public static PhotoVendorInfo create(VendorResponse vendor) {
        return PhotoVendorInfo.builder()
                .wholeSalerId(vendor.getVendorId().intValue())
                .companyName(vendor.getVendorName())
                .phone(vendor.getAddresses().get(0).getPhone())
                .build();
    }
}
