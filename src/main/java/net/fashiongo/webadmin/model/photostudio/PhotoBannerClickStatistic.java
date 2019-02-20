package net.fashiongo.webadmin.model.photostudio;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by jinwoo on 2019. 2. 6..
 */
public class PhotoBannerClickStatistic implements Serializable {

    private Integer wholeSalerId;

    private String wholeSalerCompanyName;

    private Integer bannerType;

    private Integer bannerClickCount;

    public PhotoBannerClickStatistic(Integer wholeSalerId, String wholeSalerCompanyName, Integer bannerType, Long count) {
        this.wholeSalerId = wholeSalerId;
        this.wholeSalerCompanyName = wholeSalerCompanyName;
        this.bannerType = bannerType;
        this.bannerClickCount = count.intValue();
    }

    public Integer getBannerClickCount() {
        return bannerClickCount;
    }

    public void setBannerClickCount(Integer bannerClickCount) {
        this.bannerClickCount = bannerClickCount;
    }

    public Integer getBannerType() {
        return bannerType;
    }

    public void setBannerType(Integer bannerType) {
        this.bannerType = bannerType;
    }

    public String getWholeSalerCompanyName() {
        return wholeSalerCompanyName;
    }

    public void setWholeSalerCompanyName(String wholeSalerCompanyName) {
        this.wholeSalerCompanyName = wholeSalerCompanyName;
    }

    public Integer getWholeSalerId() {
        return wholeSalerId;
    }

    public void setWholeSalerId(Integer wholeSalerId) {
        this.wholeSalerId = wholeSalerId;
    }

    public static List<PhotoBannerClickStatistic> build(List<Tuple> results) {

        return results.stream().map((tuple) -> {
            return new PhotoBannerClickStatistic(tuple.get("wholeSalerId", Integer.class),
                    tuple.get("wholeSalerCompanyName", String.class), tuple.get("bannerType", Integer.class),
                    tuple.get("count", Long.class));
        }).collect(Collectors.toList());
    }
}
