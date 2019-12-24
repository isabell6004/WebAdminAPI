package net.fashiongo.webadmin.data.model.statistics;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class GetVendorsGeneralInfoParameter {
    @JsonProperty(value = "pn")
    private Integer pn;

    @JsonProperty(value = "ps")
    private Integer ps;

    @JsonProperty(value = "so")
    private String so;

    @JsonProperty(value = "sq")
    private String sq;

    @JsonProperty(value = "vendorStatus")
    private Integer vendorStatus;

    @JsonProperty(value = "vendorCategory")
    private Integer vendorCategory;

    @JsonProperty(value = "vendorType")
    private String vendorType;

    @JsonProperty(value = "location")
    private String location;

    @JsonProperty(value = "state")
    private String state;

    @JsonProperty(value = "assignedUser")
    private String assignedUser;

    @JsonProperty(value = "orderBy")
    private String orderBy;
}
