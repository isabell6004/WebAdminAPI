package net.fashiongo.webadmin.data.model.helpcenter.response;

import lombok.Getter;
import lombok.Setter;
import org.codehaus.jackson.annotate.JsonProperty;

@Getter
@Setter
public class CategoryGroupsResponse {
    @JsonProperty(value = "categoryGroupId")
    private Integer categoryGroupId;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "categoryCount")
    private Integer categoryCount;
}
