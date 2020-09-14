package net.fashiongo.webadmin.service.vendor;

import net.fashiongo.webadmin.data.model.vendor.response.ActiveVendorResponse;
import net.fashiongo.webadmin.data.model.vendor.response.VendorAutocompleteResponse;
import net.fashiongo.webadmin.data.model.vendor.response.VendorResponse;

import java.time.LocalDateTime;
import java.util.List;

public interface VendorNewService {
    List<ActiveVendorResponse> getActiveVendors();

    List<VendorAutocompleteResponse> getShopActiveVendors(String prefix);

    VendorResponse getVendorById(Long vendorId);

    void setVendorHold(Long vendorId, Integer active, LocalDateTime holdDate);
}
