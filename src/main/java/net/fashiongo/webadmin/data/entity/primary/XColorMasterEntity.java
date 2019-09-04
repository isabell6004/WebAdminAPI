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

@Getter
@Setter
@Entity
@Table(name = "XColorMaster")
public class XColorMasterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ColorListID")
    @Setter(AccessLevel.NONE)
    private Integer colorListID;

    @Column(name = "MasterColorName")
    private String masterColorName;

    @Column(name = "ColorName")
    private String colorName;

    @Column(name = "Active")
    private Boolean active;
}
