package net.fashiongo.webadmin.data.model.vendor;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AssignedUser {
    @JsonProperty(value = "UserID")
    private Integer userID;

    @JsonProperty(value = "UserName")
    private String userName;
}
