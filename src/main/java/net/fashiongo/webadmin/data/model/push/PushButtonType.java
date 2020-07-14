package net.fashiongo.webadmin.data.model.push;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Getter
public enum PushButtonType {
    REPLY("REPLY"),
    DEEP_LINK("DEEP_LINK"),
    OPEN_APP("OPEN_APP"),
    OPEN_URL("OPEN_URL"),
    DISMISS("DISMISS");

    private String value;

    PushButtonType(String value) {
        this.value = value;
    }

    private static Map<String, PushButtonType> buttonTypeMap = new HashMap<>();

    static {
        for (PushButtonType button : PushButtonType.values()) {
            buttonTypeMap.put(button.getValue(), button);
        }
    }

    public static PushButtonType get(String value) {
        return Optional.ofNullable(buttonTypeMap.get(value))
                .orElseThrow(() -> new IllegalArgumentException("cannot find a value, " + value));
    }
}
