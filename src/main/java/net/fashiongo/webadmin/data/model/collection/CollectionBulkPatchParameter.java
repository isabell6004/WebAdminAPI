package net.fashiongo.webadmin.data.model.collection;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class CollectionBulkPatchParameter {
    @JsonProperty(value = "collectionList")
    private List<CollectionBulkSaveParameter> collectionList;
}
