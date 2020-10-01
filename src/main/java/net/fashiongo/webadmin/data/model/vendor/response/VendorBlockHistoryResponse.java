package net.fashiongo.webadmin.data.model.vendor.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class VendorBlockHistoryResponse {
    private Long reasonId;
    private Integer typeCode;
    private String reasonTitle;
    private String reasonDescription;
    private Boolean isBlock;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private LocalDateTime blockDate;
    private String blockBy;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private LocalDateTime createdOn;
    private String createdBy;
}
