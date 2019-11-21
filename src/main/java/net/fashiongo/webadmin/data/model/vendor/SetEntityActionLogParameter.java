package net.fashiongo.webadmin.data.model.vendor;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class SetEntityActionLogParameter {
    @JsonProperty(value = "EntityTypeID")
    private Integer entityTypeID;

    @JsonProperty(value = "WholeSalerID")
    private Integer wholeSalerID;

    @JsonProperty(value = "ActionID")
    private Integer actionID;
}
