package net.fashiongo.webadmin.data.model.vendor.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Setter;
import lombok.Getter;
import lombok.Builder;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class VendorBlockResponse {

    private Long vendorId;

    private String companyName;

    private Integer typeCode;

    private Long reasonId;

    private String reasonTitle;

    private String reasonDescription;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private LocalDateTime blockDate;

    private String blockBy;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private LocalDateTime createdOn;

    private String createdBy;

    /*
    public VendorBlockResponse(Long vendorId, String companyName, Integer typeCode, Integer reasonId, String reasonTitle
            , String reasonDescription, LocalDateTime blockDate, String blockBy, LocalDateTime createdOn, String createdBy) {
        this.vendorId = vendorId;
        this.companyName = companyName;
        this.typeCode = typeCode;
        this.reasonId = reasonId;
        this.reasonTitle = reasonTitle;
        this.reasonDescription = reasonDescription;
        this.blockDate = blockDate == null ? null : blockDate;
        this.blockBy = blockBy;
        this.createdOn = createdOn == null ? null : createdOn;
        this.createdBy = createdBy;
        if (this.typeCode == 1) {
            this.typeCodeName = "Vednor Admin Log-in Block";
        }
        if (this.typeCode == 2) {
            this.typeCodeName = "AD Block";
        }
        if (this.typeCode == 3) {
            this.typeCodeName = "Payout Block";
        }
    } */
}

