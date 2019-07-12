package net.fashiongo.webadmin.data.model.admin;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class LoginControl {
    @JsonProperty("ControlID")
    private Integer controlID;

    @JsonProperty("UserID")
    private Integer userID;

    @JsonProperty("Weekday")
    private Integer weekday;

    @JsonProperty("TimeFrom")
    private LocalDateTime timeFrom;

    @JsonProperty("TimeTo")
    private LocalDateTime timeTo;

    @JsonProperty("Allow")
    private Boolean allow;

    @JsonProperty("Active")
    private Boolean active;
}
