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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "Map_CollectionCategory")
public class MapCollectionCategoryEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MapID")
    @Setter(AccessLevel.NONE)
    private Integer mapID;

    @Column(name = "CollectionCategoryID")
    private Integer collectionCategoryID;

    @Column(name = "CategoryID")
    private Integer categoryID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CategoryID", updatable = false, insertable = false)
    private CategoryEntity category;
}
