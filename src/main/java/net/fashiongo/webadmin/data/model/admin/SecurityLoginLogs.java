package net.fashiongo.webadmin.data.model.admin;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class SecurityLoginLogs {
    @JsonProperty("LogID")
    private Integer logID;
    @JsonProperty("UserName")
    private String userName;
    @JsonProperty("IP")
    private String ip;
    @JsonProperty("LoginOn")
    private LocalDateTime loginOn;
}
