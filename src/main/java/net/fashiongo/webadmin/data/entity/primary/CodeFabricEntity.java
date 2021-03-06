package net.fashiongo.webadmin.data.entity.primary;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import net.fashiongo.webadmin.model.primary.MapFabricCategory;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Code_Fabric")
public class CodeFabricEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FabricID")
    @Setter(AccessLevel.NONE)
    private Integer fabricId;

    @Column(name = "FabricName")
    private String fabricName;

    @Column(name = "Active", nullable = false)
    private boolean active;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "FabricID")
    private List<MapFabricCategoryEntity> mapFabricCategoryList;

}
