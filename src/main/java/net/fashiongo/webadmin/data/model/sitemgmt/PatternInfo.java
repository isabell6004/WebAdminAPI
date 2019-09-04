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
public class PatternInfo {
    @JsonProperty("PatternID")
    private Integer patternID;

    @JsonProperty("PatternName")
    private String patternName;

    @JsonProperty("Active")
    private Boolean active;
}
