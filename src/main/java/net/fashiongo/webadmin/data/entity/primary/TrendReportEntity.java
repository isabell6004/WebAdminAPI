package net.fashiongo.webadmin.data.entity.primary;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "TrendReport")
public class TrendReportEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Column(name = "TrendReportID")
    private Integer trendReportID;

    @Column(name = "Title")
    private String title;

    @Column(name = "Image")
    private String image;

    @Column(name = "DateFrom")
    private LocalDateTime dateFrom;

    @Column(name = "DateTo")
    private LocalDateTime dateTo;

    @Column(name = "ListOrder")
    private Integer listOrder;

    @Column(name = "Active")
    private Boolean active;

    @Column(name = "CreatedOn")
    private LocalDateTime createdOn;

    @Column(name = "CreatedBy")
    private String createdBy;

    @Column(name = "TRDescription")
    private String trDescription;

    @Column(name = "MiniImage")
    private String miniImage;

    @Column(name = "SquareImage")
    private String squareImage;

    @Column(name = "ModifiedOn")
    private LocalDateTime modifiedOn;

    @Column(name = "ModifiedBy")
    private String modifiedBy;

    @Column(name = "Sticky")
    private Boolean sticky;

    @Column(name = "KMMImage")
    private String kmmImage;

    @Column(name = "KMMImage1")
    private String kmmImage1;

    @Column(name = "KMMImage2")
    private String kmmImage2;

    @Column(name = "ShowID")
    private Integer showID;

    @Column(name = "ShowScheduleID")
    private Integer showScheduleID;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "TrendReportID")
    private List<TrendReportMapEntity> trendReportMap;
}

