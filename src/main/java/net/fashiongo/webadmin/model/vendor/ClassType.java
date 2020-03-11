package net.fashiongo.webadmin.model.vendor;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Created by jinwoo on 2020-02-12.
 */
public enum ClassType {

    GENERAL(1),
    PREMIUM(2),
    ;

    private int value;

    ClassType(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    private static Map<Integer, ClassType> vendorClassType = new HashMap<>();
    static {
        for (ClassType s : ClassType.values()) {
            vendorClassType.put(s.getValue(), s);
        }
    }

    public static ClassType get(int value) {
        return Optional.ofNullable(vendorClassType.get(value))
                .orElseThrow(() -> new IllegalArgumentException("cannot find a value, " + value));
    }
}
