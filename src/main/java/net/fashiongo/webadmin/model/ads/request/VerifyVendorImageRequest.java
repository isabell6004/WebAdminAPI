package net.fashiongo.webadmin.model.ads.request;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
public class VerifyVendorImageRequest {

    @NotNull(message = "vendorIds are required.")
    private List<Integer> vendorIds;

    @NotNull(message = "vendorImageTypes are required.")
    private List<Integer> vendorImageTypes;
}
