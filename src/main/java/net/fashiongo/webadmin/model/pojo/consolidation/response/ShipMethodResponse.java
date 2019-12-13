package net.fashiongo.webadmin.model.pojo.consolidation.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ShipMethodResponse {
    @JsonProperty(value = "ShipMethodID") private int shipMethodId;
    @JsonProperty(value = "ShipMethodName") private String shipMethodName;
}
