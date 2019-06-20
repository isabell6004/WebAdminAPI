package net.fashiongo.webadmin.model.photostudio;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ModelOption {
    private String imageUrl;

    private Boolean isBooked;

    @JsonProperty("modelID")
    private Integer modelId;

    private String modelName;
}
