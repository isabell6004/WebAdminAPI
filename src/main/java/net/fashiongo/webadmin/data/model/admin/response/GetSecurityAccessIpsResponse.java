package net.fashiongo.webadmin.data.model.admin.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import net.fashiongo.webadmin.data.model.admin.SecurityListIP;

import java.util.List;

@Getter
@Builder
public class GetSecurityAccessIpsResponse {
    @JsonProperty("Table")
    List<SecurityListIP> securityListIP;
}
