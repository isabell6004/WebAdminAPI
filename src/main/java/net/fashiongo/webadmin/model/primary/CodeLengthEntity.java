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
@Table(name = "Code_Length")
public class CodeLengthEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LengthID")
    @Setter(AccessLevel.NONE)
    private Integer lengthId;

    @Column(name = "LengthName")
    private String lengthName;

    @Column(name = "Active")
    private Boolean active;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "LengthID")
    private List<MapLengthCategory> mapLengthCategoryList;
}
