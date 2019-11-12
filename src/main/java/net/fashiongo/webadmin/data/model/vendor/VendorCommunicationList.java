package net.fashiongo.webadmin.data.model.vendor;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class VendorCommunicationList {
    @JsonProperty(value = "CommunicationID")
    private Integer communicationID;

    @JsonProperty(value = "Notes")
    private String notes;

    @JsonProperty(value = "CreatedOn")
    private Timestamp createdOn;

    @JsonProperty(value = "CreatedBy")
    private String createdBy;

    @JsonProperty(value = "ReasonID")
    private Integer reasonID;
}
