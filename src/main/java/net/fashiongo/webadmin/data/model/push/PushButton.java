package net.fashiongo.webadmin.data.model.push;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PushButton {
    private String name;
    private PushButtonType buttonType;
    private String link;
    private String hint;

    public PushButton() {}

    @Builder
    public PushButton(String name, PushButtonType buttonType, String link, String hint) {
        this.name = name;
        this.buttonType = buttonType;
        this.link = link;
        this.hint = hint;
    }
}
