package net.fashiongo.webadmin.model.vendor;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Created by jinwoo on 2020-01-08.
 */
public enum BannerType {
    LOGO(1),
    DESKTOP_MAIN_BIG(2),
    DESKTOP_MAIN_SMALL(3),
    MOBILE_MAIN(4),
    MOBILE_NEW_VENDOR(5),
    VENDOR_HOME(6),
    AD_BANNER(7),
    PREMIUM_HOME_BANNER(8),
    PREMIUM_HOME_VIDEO(9);

    private int value;

    public int getValue() {
        return this.value;
    }

    BannerType(int value) {
        this.value = value;
    }

    private static Map<Integer, BannerType> bannerTypeMap = new HashMap<>();
    static {
        for (BannerType b : BannerType.values()) {
            bannerTypeMap.put(b.getValue(), b);
        }
    }

    public static BannerType get(int value) {
        return Optional.ofNullable(bannerTypeMap.get(value))
                .orElseThrow(() -> new IllegalArgumentException("cannot find a value, " + value));
    }
}
