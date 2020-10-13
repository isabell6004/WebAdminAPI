package net.fashiongo.webadmin.data.model.vendor.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class VendorContentFileResponse {
    private Integer vendorContentFileId;
    private Integer vendorContentId;
    private Integer fileType;
    private String fileName;
    private Integer listOrder;

    public VendorContentFileResponse() {
    }

    @Builder
    public VendorContentFileResponse(Integer vendorContentFileId, Integer vendorContentId, Integer fileType,
                                     String fileName, Integer listOrder) {
        this.vendorContentFileId = vendorContentFileId;
        this.vendorContentId = vendorContentId;
        this.fileType = fileType;
        this.fileName = fileName;
        this.listOrder = listOrder;
    }
}
