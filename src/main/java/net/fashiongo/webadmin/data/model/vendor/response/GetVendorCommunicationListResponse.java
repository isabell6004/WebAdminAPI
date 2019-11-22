package net.fashiongo.webadmin.data.model.vendor.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import net.fashiongo.webadmin.data.model.vendor.VendorCommunicationList;

import java.util.List;

@Builder
public class GetVendorCommunicationListResponse {
    @JsonProperty(value = "Vclist")
    List<VendorCommunicationList> vclist;
}
