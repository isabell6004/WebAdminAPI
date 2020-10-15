package net.fashiongo.webadmin.data.model.vendor;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class VendorBlockList {

    @JsonProperty(value = "VendorID")
    private Integer vendorId;

    @JsonProperty(value = "CompanyName")
    private String companyName;

    @JsonProperty(value = "TypeCode")
    private Integer typeCode;

    @JsonProperty(value = "TypeCodeName")
    private String typeCodeName;

    @JsonProperty(value = "ReasonID")
    private Integer reasonId;

    @JsonProperty(value = "ReasonTitle")
    private String reasonTitle;

    @JsonProperty(value = "reasonDescription")
    private String reasonDescription;

    @JsonProperty(value = "blockDate")
    private LocalDateTime blockDate;

    @JsonProperty(value = "blockBy")
    private String blockBy;

    @JsonProperty(value = "createdOn")
    private LocalDateTime createdOn;

    @JsonProperty(value = "createdBy")
    private String createdBy;

    public VendorBlockList(Integer vendorId, String companyName, Integer typeCode, Integer reasonId, String reasonTitle
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
    }
}
