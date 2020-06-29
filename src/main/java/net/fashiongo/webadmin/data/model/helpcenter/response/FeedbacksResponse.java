package net.fashiongo.webadmin.data.model.helpcenter.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeedbacksResponse {
    @JsonProperty(value = "isHelpful")
    private Boolean isHelpful;

    @JsonProperty(value = "feedbackReasonId")
    private Integer feedbackReasonId;

    @JsonProperty(value = "count")
    private Integer count;
}
