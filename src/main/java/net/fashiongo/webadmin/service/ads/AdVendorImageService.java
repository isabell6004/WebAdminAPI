package net.fashiongo.webadmin.service.ads;

import net.fashiongo.webadmin.model.ads.response.VerifyVendorImageResponse;

import java.util.List;

public interface AdVendorImageService {

    List<VerifyVendorImageResponse> getVerifyVendorImages(List<Integer> vendorIds, List<Integer> vendorImageTypes);
}
