package net.fashiongo.webadmin.data.model.admin.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import net.fashiongo.webadmin.data.model.admin.SecurityLoginLogs;
import net.fashiongo.webadmin.data.model.admin.SecurityLogsColumn;

import java.util.List;

@Getter
@Builder
public class GetSecurityLogsResponse {
    @JsonProperty("Table")
    private List<SecurityLoginLogs> securityLoginLogs;

    @JsonProperty("Table1")
    private List<SecurityLogsColumn> securityLogsColumn;
}
