package net.fashiongo.webadmin.data.entity.primary;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import net.fashiongo.webadmin.model.primary.MapPatternCategory;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Code_Pattern")
public class CodePatternEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PatternID")
    @Setter(AccessLevel.NONE)
    private Integer patternId;

    @Column(name = "PatternName")
    private String patternName;

    @Column(name = "Active", nullable = false)
    private boolean active;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "PatternID")
    private List<MapPatternCategoryEntity> mapPatternCategoryList;
}
