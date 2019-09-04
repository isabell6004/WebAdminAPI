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
public class ColorListInfo {
    @JsonProperty("ColorListID")
    private Integer colorListID;

    @JsonProperty("MasterColorName")
    private String masterColorName;

    @JsonProperty("ColorName")
    private String colorName;

    @JsonProperty("Active")
    private Boolean active;
}
