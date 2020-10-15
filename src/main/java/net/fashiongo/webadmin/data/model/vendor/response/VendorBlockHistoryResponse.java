package net.fashiongo.webadmin.data.model.vendor.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class VendorBlockHistoryResponse {
    private Long reasonId;
    private Integer typeCode;
    private String reasonTitle;
    private String reasonDescription;
    private Boolean isBlock;
    private LocalDateTime blockDate;
    private String blockBy;
    private LocalDateTime createdOn;
    private String createdBy;

    public VendorBlockHistoryResponse() {}
}
