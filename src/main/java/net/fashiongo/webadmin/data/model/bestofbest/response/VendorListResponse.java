package net.fashiongo.webadmin.data.model.bestofbest.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VendorListResponse {
    private Long vendorId;
    private String vendorName;
    private Integer status;
    private Boolean listStatus;
}