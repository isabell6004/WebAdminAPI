package net.fashiongo.webadmin.data.model.admin;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SecurityListIP {
    @JsonProperty("IPID")
    private Integer ipID;

    @JsonProperty("IPAddress")
    private String ipAddress;

    @JsonProperty("Description")
    private String description;
}
