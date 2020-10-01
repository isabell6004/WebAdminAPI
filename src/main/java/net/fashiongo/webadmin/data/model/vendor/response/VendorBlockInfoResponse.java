package net.fashiongo.webadmin.data.model.vendor.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class VendorBlockInfoResponse {
    private Boolean isBlock;
    private Boolean isAdBlock;
    private Boolean isPayoutBlock;
    private List<VendorBlockHistoryResponse> history = new ArrayList<>();
}
