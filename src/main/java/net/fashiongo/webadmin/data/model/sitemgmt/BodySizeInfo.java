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
public class BodySizeInfo {
    @JsonProperty("BodySizeID")
    private Integer bodySizeID;

    @JsonProperty("BodySizeName")
    private String bodySizeName;

    @JsonProperty("TitleImage")
    private String titleImage;

    @JsonProperty("CategoryID")
    private Integer categoryID;

    @JsonProperty("Active")
    private Boolean active;
}
