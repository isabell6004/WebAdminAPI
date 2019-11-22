package net.fashiongo.webadmin.data.model.vendor;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class SetNewPasswordParameter {
    @JsonProperty(value = "userid")
    private String userid;

    @JsonProperty(value = "newpassword")
    private String newpassword;
}
