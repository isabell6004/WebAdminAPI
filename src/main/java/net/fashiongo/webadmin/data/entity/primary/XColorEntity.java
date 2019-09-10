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
@Table(name = "XColor")
public class XColorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Column(name = "ColorID")
    private Integer colorID;

    @Column(name = "WholeSalerID")
    private Integer wholeSalerID;

    @Column(name = "Color")
    private String color;

    @Column(name = "StartingDate")
    private LocalDateTime startingDate;

    @Column(name = "ColorListID")
    private Integer colorListID;

    @Column(name = "LastUser")
    private String lastUser;

    @Column(name = "LastModifiedDateTime")
    private LocalDateTime lastModifiedDateTime;

    @Column(name = "Image")
    private String image;

    @Column(name = "Active")
    private Boolean active;

    @Column(name = "ActiveOld")
    private String ActiveOld;
}
