package net.fashiongo.webadmin.model.primary;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Code_Style")
public class CodeStyleEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "StyleID")
    @Setter(AccessLevel.NONE)
    private Integer styleId;

    @Column(name = "StyleName")
    private String styleName;

    @Column(name = "Active")
    private boolean active;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "StyleID")
    private List<MapStyleCategory> mapStyleCategoryList;
}
