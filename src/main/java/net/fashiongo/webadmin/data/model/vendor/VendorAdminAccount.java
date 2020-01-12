package net.fashiongo.webadmin.data.model.vendor;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
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

    public VendorAdminAccount(LocalDateTime createdOn, String userID, String userName, String userType, Timestamp lastActivityDate) {
        this.createdOn = createdOn;
        this.userID = userID;
        this.userName = userName;
        this.userType = userType;
        this.lastActivityDate = lastActivityDate.toLocalDateTime();
    }
}
