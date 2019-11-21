package net.fashiongo.webadmin.data.model.vendor;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class SetVendorContractDocumentParameter {
    @JsonProperty(value = "VendorContractDocumentID")
    private Integer vendorContractDocumentID;

    @JsonProperty(value = "VendorContractID")
    private Integer vendorContractID;

    @JsonProperty(value = "DocumentTypeID")
    private Integer documentTypeID;

    @JsonProperty(value = "FileName")
    private String fileName;

    @JsonProperty(value = "FileName2")
    private String fileName2;

    @JsonProperty(value = "FileName3")
    private String fileName3;

    @JsonProperty(value = "Note")
    private String note;

    @JsonProperty(value = "ReceivedBy")
    private String receivedBy;
}
