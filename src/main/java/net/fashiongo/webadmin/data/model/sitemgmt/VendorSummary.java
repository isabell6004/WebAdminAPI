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
public class VendorSummary {
    @JsonProperty("WholeSalerID")
    private Integer wholeSalerID;

    @JsonProperty("CompanyName")
    private String companyName;

    @JsonProperty("DirName")
    private String dirName;

    @JsonProperty("ItemCount")
    private Long itemCount;

    @JsonProperty("AccessStatus")
    private Integer accessStatus;
}
