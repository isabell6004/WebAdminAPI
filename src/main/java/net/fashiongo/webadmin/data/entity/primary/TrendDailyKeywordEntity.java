package net.fashiongo.webadmin.data.entity.primary;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "trend_daily_keyword")
public class TrendDailyKeywordEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Column(name = "trend_daily_keyword_id")
    private long trendDailyKeywordID;

    @Column(name = "expose_date")
    private LocalDateTime exposeDate;

    @Column(name = "keyword_text")
    private String keywordText;

    @Column(name = "keyword_type")
    private int keywordType;

    @Column(name = "sort_no")
    private int sortNo;

    @Column(name = "category_id")
    private Integer categoryID;

    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "modified_on")
    private LocalDateTime modifiedOn;

    @Column(name = "modified_by")
    private String modifiedBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "CategoryID", updatable = false, insertable = false)
    private CategoryEntity category;
}
