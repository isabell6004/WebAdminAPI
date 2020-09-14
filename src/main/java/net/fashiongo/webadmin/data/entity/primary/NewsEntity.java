package net.fashiongo.webadmin.data.entity.primary;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "tblNews")
@DynamicUpdate
public class NewsEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NewsID")
    @Setter(AccessLevel.NONE)
    private Integer newsId;

    @Column(name = "WholeSalerID")
    private Integer wholeSalerId;

    @Column(name = "NewsTitle")
    private String newsTitle;

    @Column(name = "NewsContent")
    private String newsContent;

    @Column(name = "StartingDate")
    private LocalDateTime startingDate;

    @Column(name = "NewsType")
    private String newsType;

    @Column(name = "FromDate")
    private LocalDateTime fromDate;

    @Column(name = "ToDate")
    private LocalDateTime toDate;

    @Column(name = "LastUser")
    private String lastUser;

    @Column(name = "LastModifiedDateTime")
    private LocalDateTime lastModifiedDateTime;

    @Column(name = "SortNo")
    private int sortNo;

    @Column(name = "Active")
    private boolean active;

    @Column(name = "ActiveOld")
    private String activeOld;

    @Column(name = "ShowBanner")
    private boolean showBanner;
}
