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

@Getter
@Setter
@Entity
@Table(name = "XSizeChart")
public class XSizeChartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Column(name = "SizeID")
    private Integer sizeID;

    @Column(name = "WholeSalerID")
    private Integer wholeSalerID;

    @Column(name = "SizeName")
    private String sizeName;

    @Column(name = "Description")
    private String description;

    @Column(name = "S1")
    private String s1;

    @Column(name = "S2")
    private String s2;

    @Column(name = "S3")
    private String s3;

    @Column(name = "S4")
    private String s4;

    @Column(name = "S5")
    private String s5;

    @Column(name = "S6")
    private String s6;

    @Column(name = "S7")
    private String s7;

    @Column(name = "S8")
    private String s8;

    @Column(name = "S9")
    private String s9;

    @Column(name = "S10")
    private String s10;

    @Column(name = "S11")
    private String s11;

    @Column(name = "StartingDate")
    private LocalDateTime startingDate;

    @Column(name = "Active")
    private Boolean active;

    @Column(name = "LastUser")
    private String lastUser;

    @Column(name = "LastModifiedDateTime")
    private LocalDateTime lastModifiedDateTime;
}
