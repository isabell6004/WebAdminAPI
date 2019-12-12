package net.fashiongo.webadmin.data.model.vendor;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class VendorAdminAccount {
    @JsonProperty(value = "CreatedOn")
    private LocalDateTime createdOn;

    @JsonProperty(value = "UserID")
    private String userID;

    @JsonProperty(value = "UserName")
    private String userName;

    @JsonProperty(value = "UserType")
    private String userType;

    @JsonProperty(value = "LastActivityDate")
    private LocalDateTime lastActivityDate;
}
