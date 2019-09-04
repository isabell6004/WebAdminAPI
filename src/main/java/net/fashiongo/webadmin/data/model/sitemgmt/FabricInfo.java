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
public class FabricInfo {
    @JsonProperty("FabricID")
    private Integer fabricID;

    @JsonProperty("FabricName")
    private String fabricName;

    @JsonProperty("Active")
    private Boolean active;
}
