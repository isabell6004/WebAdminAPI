package net.fashiongo.webadmin.data.model.sitemgmt;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryVendor {
    @JsonProperty("WholeSalerID")
    private Integer wholeSalerID;

    @JsonProperty("CompanyName")
    private String companyName;
}
