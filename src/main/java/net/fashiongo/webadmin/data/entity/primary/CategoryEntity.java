package net.fashiongo.webadmin.data.entity.primary;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import net.fashiongo.webadmin.model.primary.MapFabricCategory;
import net.fashiongo.webadmin.model.primary.MapLengthCategory;
import net.fashiongo.webadmin.model.primary.MapPatternCategory;
import net.fashiongo.webadmin.model.primary.MapStyleCategory;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "Category")
public class CategoryEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CategoryID")
    @Setter(AccessLevel.NONE)
    private Integer categoryId;

    @Column(name = "CategoryName")
    private String categoryName;

    @Column(name = "CategoryName2")
    private String categoryName2;

    @Column(name = "CategoryDescription")
    private String categoryDescription;

    @Column(name = "ParentCategoryID", nullable = false)
    private int parentCategoryId;

    @Column(name = "ParentParentCategoryID")
    private Integer parentParentCategoryId;

    @Column(name = "Lvl", nullable = false)
    private int lvl;

    @Column(name = "TitleImage")
    private String titleImage;

    @Column(name = "IsLandingPage", nullable = false)
    private boolean isLandingPage;

    @Column(name = "IsFeatured", nullable = false)
    private boolean isFeatured;

    @Column(name = "ListOrder", nullable = false)
    private int listOrder;

    @Column(name = "Active", nullable = false)
    private boolean active;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "CategoryID")
    private Set<MapFabricCategory> mapFabricCategoryList;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "CategoryID")
    private Set<MapLengthCategory> mapLengthCategoryList;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "CategoryID")
    private Set<MapPatternCategory> mapPatternCategoryList;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "CategoryID")
    private Set<MapStyleCategory> mapStyleCategoryList;
}
