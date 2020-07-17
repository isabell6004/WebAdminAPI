package net.fashiongo.webadmin.data.model.collection.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CollectionListResponse {
    @JsonProperty(value = "collectionId")
    private Integer collectionId;

    @JsonProperty(value = "title")
    private String title;

    @JsonProperty(value = "collectionType")
    private Integer collectionType;

    @JsonProperty(value = "completedOn")
    private LocalDateTime completedOn;

    @JsonProperty(value = "completedBy")
    private String completedBy;

    @JsonProperty(value = "status")
    private Integer status;
}
