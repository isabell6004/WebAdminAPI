package net.fashiongo.webadmin.model.vendor;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Created by jinwoo on 2020-01-07.
 */
public enum EmailType {

    ORDER(1),
    CS(2),
    BILLING1(3),
    BILLING2(4),
    BILLING3(5);

    private int value;

    EmailType(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    private static Map<Integer, EmailType> emailTypeMap = new HashMap<>();

    static {
        for (EmailType e : EmailType.values()) {
            emailTypeMap.put(e.getValue(), e);
        }
    }

    public static EmailType get(int value) {
        return Optional.ofNullable(emailTypeMap.get(value))
                .orElseThrow(() -> new IllegalArgumentException("cannot find a value, " + value));
    }
}
