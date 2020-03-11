package net.fashiongo.webadmin.model.vendor;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Created by jinwoo on 2020-01-10.
 */
public enum StatusType {

    INACTIVE(0),
    ACTIVE(1),
    SHOP_ACTIVE(2),
    ORDER_ACTIVE(3),
    CLOSED(4);

    private int value;

    StatusType(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    private static Map<Integer, StatusType> vendorStatusMap = new HashMap<>();
    static {
        for (StatusType s : StatusType.values()) {
            vendorStatusMap.put(s.getValue(), s);
        }
    }

    public static StatusType get(int value) {
        return Optional.ofNullable(vendorStatusMap.get(value))
                .orElseThrow(() -> new IllegalArgumentException("cannot find a value, " + value));
    }

    public static StatusType getStatusType(Boolean active, Boolean shopActive, Boolean orderActive) {
        StatusType vendorStatusType = StatusType.INACTIVE;
        if(orderActive) {
            vendorStatusType = StatusType.ORDER_ACTIVE;
        } else if (shopActive) {
            vendorStatusType = StatusType.SHOP_ACTIVE;
        } else if (active) {
            vendorStatusType = StatusType.ACTIVE;
        }
        return vendorStatusType;
    }

}
