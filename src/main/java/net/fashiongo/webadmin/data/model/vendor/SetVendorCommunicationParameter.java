package net.fashiongo.webadmin.data.model.vendor;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class SetVendorCommunicationParameter {
    @JsonProperty(value = "CommunicationID")
    private Integer communicationID;

    @JsonProperty(value = "Notes")
    private String notes;

    @JsonProperty(value = "wid")
    private Integer wid;
}
