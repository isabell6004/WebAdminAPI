package net.fashiongo.webadmin.data.entity.primary;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "Product_Video")
public class ProductVideoEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Column(name = "ProductVideoID")
    private Integer productVideoID;

    @Column(name = "ProductID")
    private Integer productID;

    @Column(name = "VideoName")
    private String videoName;

    @Column(name = "ListOrder")
    private Integer listOrder;

    @Column(name = "Active")
    private Boolean active;
}
