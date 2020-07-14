package net.fashiongo.webadmin.data.model.push;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PushSendParameter {
    private String type;
    private String targetType;
    private List<String> uids;
    private String title;
    private String body;
    private String contents;
    private List<String> images;
    private String clickAction;
    private String clickActionType;
    private String contact;
    private String removeGuide;
    private List<PushButton> buttons;
    private long tagLimit = 100;
    @JsonProperty(value = "isEvent")
    private boolean isEvent = false;

    public PushSendParameter() {}

    @Builder
    public PushSendParameter(String type, String targetType, List<String> uids, String title, String body, String contents, List<String> images, String clickAction, String clickActionType, String contact, String removeGuide, List<PushButton> buttons, long tagLimit, boolean isEvent) {
        this.type = type;
        this.targetType = targetType;
        this.uids = uids;
        this.title = title;
        this.body = body;
        this.contents = contents;
        this.images = images;
        this.clickAction = clickAction;
        this.clickActionType = clickActionType;
        this.contact = contact;
        this.removeGuide = removeGuide;
        this.buttons = buttons;
        this.tagLimit = tagLimit;
        this.isEvent = isEvent;
    }
}
