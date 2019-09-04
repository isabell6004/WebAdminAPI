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
public class StyleInfo {
    @JsonProperty("StyleID")
    private Integer styleID;

    @JsonProperty("StyleName")
    private String styleName;

    @JsonProperty("Active")
    private Boolean active;
}
