package net.fashiongo.webadmin.data.model.vendor.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class VendorAddressSummaryResponse {
    private Integer typeCode;
    private String name;
    private String streetNo1;
    private String streetNo2;
    private String city;
    private String state;
    private String zipCode;
    private String country;
    private String phone;
    private String phone2;
    private String fax;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdOn;
    private String createdBy;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime modifiedOn;
    private String modifiedBy;
}
