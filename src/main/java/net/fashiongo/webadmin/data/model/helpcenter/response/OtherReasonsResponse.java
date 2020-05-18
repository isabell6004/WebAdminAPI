package net.fashiongo.webadmin.data.model.helpcenter.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class OtherReasonsResponse {
    @JsonProperty(value = "reason")
    private String reason;

    @JsonProperty(value = "createdOn")
    private LocalDateTime createdOn;
}
