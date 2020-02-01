package net.fashiongo.webadmin.data.model.vendor;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public enum AccountType {
    MASTER(1),
    SUB(2);

    private int value;

    AccountType(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    private static Map<Integer, AccountType> accountTypeMap = new HashMap<>();
    static {
        for (AccountType a : AccountType.values()) {
            accountTypeMap.put(a.getValue(), a);
        }
    }

    public static AccountType get(int value) {
        return Optional.ofNullable(accountTypeMap.get(value)).orElse(null);
    }
}
