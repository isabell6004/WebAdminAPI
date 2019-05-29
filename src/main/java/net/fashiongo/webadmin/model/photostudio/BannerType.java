package net.fashiongo.webadmin.model.photostudio;

import org.springframework.boot.Banner;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Created by jinwoo on 2019. 2. 6..
 */
public enum BannerType {

//    AdsCloseButton(1),
    AdsDoNotShowAgain(2),
//    AdsTryTop(3),
    AdsTryBottom(4),
    OrderMenuClick(5),
    BannerClick(6),
    ;

    private static Map<Integer, BannerType> bannerTypeName = new HashMap<>();

    private Integer value;

    static {
        for(BannerType type : BannerType.values()) {
            bannerTypeName.put(type.getValue(), type);
        }
    }

    BannerType(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return this.value;
    }

    public static BannerType get(int value) {
        return Optional.ofNullable(bannerTypeName.get(value))
                .orElseThrow(() -> new IllegalArgumentException("can not find a value, " + value));
    }
}
