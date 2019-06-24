package net.fashiongo.webadmin.model.primary;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VendorBlockReason {
    private VendorBlockedEntity vendorBlockedEntity;

    private String companyName;

    private String blockedReasonTitle;

    private String blockedReasonDetail;
}
