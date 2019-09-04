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
public class LengthInfo {
    @JsonProperty("LengthID")
    private Integer lengthID;

    @JsonProperty("LengthName")
    private String lengthName;

    @JsonProperty("Active")
    private Boolean active;
}
