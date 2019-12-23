package net.fashiongo.webadmin.data.model.common.reponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import net.fashiongo.webadmin.data.model.common.VendorsCompanyName;

import java.util.List;

@Builder
@Getter
public class GetVendorsResponse {
    @JsonProperty("Table")
    List<VendorsCompanyName> vendorsCompanyNames;
}
