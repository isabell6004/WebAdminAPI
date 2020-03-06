package net.fashiongo.webadmin.model.buyer;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public enum BuyerAddressType {
    BILLING(2),
    SHIPPING(4),
    COMPANY(5);
    
    private int value;
    
    BuyerAddressType(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }
    
    private static Map<Integer, BuyerAddressType> buyerAddressTypeHashMap = new HashMap<>();
    static {
        for (BuyerAddressType a : BuyerAddressType.values()) {
            buyerAddressTypeHashMap.put(a.getValue(), a);
        }
    }

    public static BuyerAddressType get(int value) {
        return Optional.ofNullable(buyerAddressTypeHashMap.get(value))
                .orElseThrow(() -> new IllegalArgumentException("cannot find a value, " + value));
    }
}
