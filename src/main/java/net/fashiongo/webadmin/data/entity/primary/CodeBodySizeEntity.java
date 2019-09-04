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
@Table(name = "Code_BodySize")
public class CodeBodySizeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BodySizeID")
    @Setter(AccessLevel.NONE)
    private Integer bodySizeID;

    @Column(name = "BodySizeName")
    private String bodySizeName;

    @Column(name = "TitleImage")
    private String titleImage;

    @Column(name = "CategoryID")
    private Integer categoryID;

    @Column(name = "Active", nullable = false)
    private boolean active;
}
