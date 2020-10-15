package net.fashiongo.webadmin.data.model.vendor.response;

import lombok.Setter;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Setter
public class VendorBlockResponse {

    private Long vendorId;
    private String companyName;
    private Integer typeCode;
    private Long reasonId;
    private String reasonTitle;
    private String reasonDescription;
    private LocalDateTime blockDate;
    private String blockBy;
    private LocalDateTime createdOn;
    private String createdBy;

    public VendorBlockResponse() {}

}

