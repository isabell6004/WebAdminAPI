package net.fashiongo.webadmin.model.photostudio;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by jinwoo on 2019. 2. 6..
 */
@Entity
@Table(name = "Photo_BannerClick")
public class PhotoBannerClick implements Serializable {

    @Id
    @Column(name = "BannerClickID")
    private Integer id;

    @Column(name = "WholeSalerID")
    private Integer wholeSalerId;

    @Column(name = "WholeSalerCompanyName")
    private String wholeSalerCompanyName;

    @Column(name = "BannerType")
    private Integer bannerType;

    @Column(name = "ClickedOn")
    private Date clickedOn;

    public PhotoBannerClick() {
    }

    public Integer getBannerType() {
        return bannerType;
    }

    public void setBannerType(Integer bannerType) {
        this.bannerType = bannerType;
    }

    public Date getClickedOn() {
        return clickedOn;
    }

    public void setClickedOn(Date clickedOn) {
        this.clickedOn = clickedOn;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}
