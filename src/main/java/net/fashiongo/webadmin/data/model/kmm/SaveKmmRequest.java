package net.fashiongo.webadmin.data.model.kmm;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class SaveKmmRequest {
    @JsonProperty(value = "payload")
    private String payload;
}
