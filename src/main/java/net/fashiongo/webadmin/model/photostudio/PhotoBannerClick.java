package net.fashiongo.webadmin.model.photostudio;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by jinwoo on 2019. 2. 6..
 */
@Entity
@Table(name = "Photo_BannerClick")
@Getter
@Setter
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
    private LocalDateTime clickedOn;
}
