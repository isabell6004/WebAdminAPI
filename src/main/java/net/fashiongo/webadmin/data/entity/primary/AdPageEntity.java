package net.fashiongo.webadmin.data.entity.primary;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Ad_Page")
public class AdPageEntity implements Serializable {
    @Id
    @Column(name = "PageID")
    private Integer pageId;

    @Column(name = "PageName")
    private String pageName;

    @Column(name = "PageUrl")
    private String pageUrl;

    @Column(name = "IsBid")
    private Boolean isBid;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "adPageEntity")
    private List<AdPageSpotEntity> adPageSpotEntity;
}
