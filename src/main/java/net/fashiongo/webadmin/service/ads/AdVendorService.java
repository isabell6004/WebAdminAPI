package net.fashiongo.webadmin.service.ads;

import net.fashiongo.webadmin.model.ads.response.AdVendorResponse;

import java.util.List;

public interface AdVendorService {

    List<AdVendorResponse> getVendorNames(List<Integer> vendorIds);
}
