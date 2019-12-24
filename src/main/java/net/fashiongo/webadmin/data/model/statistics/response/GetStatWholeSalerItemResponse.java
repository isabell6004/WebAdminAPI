package net.fashiongo.webadmin.data.model.statistics.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import net.fashiongo.webadmin.data.model.statistics.AdminServerProducts;
import net.fashiongo.webadmin.data.model.statistics.ImageServerUrl;
import net.fashiongo.webadmin.data.model.statistics.VendorAdminWebServerUrl;

import java.util.List;

@Getter
@Builder
public class GetStatWholeSalerItemResponse {
    @JsonProperty(value = "Table")
    List<VendorAdminWebServerUrl> vendorAdminWebServerUrl;

    @JsonProperty(value = "Table1")
    List<ImageServerUrl> imageServerUrl;

    @JsonProperty(value = "Table2")
    List<AdminServerProducts> adminServerProducts;

    @JsonProperty(value = "Table3")
    List<Long> totalItemCount;
}
