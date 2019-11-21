package net.fashiongo.webadmin.data.model.vendor;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class VendorHistory {

    @JsonProperty(value = "EntityActionName")
    private String entityActionName;

    @JsonProperty(value = "ActedBy")
    private String actedBy;

    @JsonProperty(value = "ActedOn")
    private LocalDateTime actedOn;

    @JsonProperty(value = "Remark")
    private String remark;
}
