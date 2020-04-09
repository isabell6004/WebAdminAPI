package net.fashiongo.webadmin.service.ads;

import net.fashiongo.webadmin.model.ads.response.AdVendorResponse;

import java.util.List;

public interface AdVendorService {

    List<AdVendorResponse> getVendorNames(Boolean shopActive, Boolean orderActive, List<Integer> vendorIds);
}
