package net.fashiongo.webadmin.model.vendor;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Created by jinwoo on 2020-01-07.
 */
public enum AddressType {

    SHOWROOM(1),
    BILLING(2),
    WAREHOUSE(3);

    private int value;

    AddressType(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    private static Map<Integer, AddressType> addressTypeMap = new HashMap<>();

    static {
        for (AddressType a : AddressType.values()) {
            addressTypeMap.put(a.getValue(), a);
        }
    }

    public static AddressType get(int value) {
        return Optional.ofNullable(addressTypeMap.get(value))
                .orElseThrow(() -> new IllegalArgumentException("cannot find a value, " + value));
    }
}
