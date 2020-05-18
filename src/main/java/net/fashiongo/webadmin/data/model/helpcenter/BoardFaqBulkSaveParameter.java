package net.fashiongo.webadmin.data.model.helpcenter;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class BoardFaqBulkSaveParameter {
    @JsonProperty(value = "faqId")
    private Integer faqId;

    @JsonProperty(value = "sortNo")
    private Integer sortNo;
}
