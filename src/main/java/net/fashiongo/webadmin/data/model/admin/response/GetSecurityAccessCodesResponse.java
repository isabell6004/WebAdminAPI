package net.fashiongo.webadmin.data.model.admin.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import net.fashiongo.webadmin.data.entity.primary.SecurityAccessCodeEntity;

import java.util.List;

@Getter
@Builder
public class GetSecurityAccessCodesResponse {
    @JsonProperty("Table")
    private List<SecurityAccessCodeEntity> securityAccessCodes;
}
