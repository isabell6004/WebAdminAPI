package net.fashiongo.webadmin.data.entity.primary;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "TrendReport_Contents")
@Getter
@Setter
public class TrendReportContentsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Column(name = "ContentID")
    private Integer contentID;

    @Column(name = "TrendReportID")
    private Integer trendReportID;

    @Column(name = "Contents")
    private String contents;

    @Column(name = "CreatedOn")
    private LocalDateTime createdOn;

    @Column(name = "CreatedBy")
    private String createdBy;

    @Column(name = "ModifiedOn")
    private LocalDateTime modifiedOn;

    @Column(name = "ModifiedBy")
    private String modifiedBy;
}
