package net.fashiongo.webadmin.model.photostudio;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Code_Photo_BackgroundColor")
@Getter
@Setter
public class CodePhotoBackgroundColor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ColorID")
    private Integer colorId;

    @Column(name = "PhotoColorName")
    private String photoColorName;

    @Column(name = "CreatedOn")
    private Date createdOn;

    @Column(name = "CreatedBy")
    private String createdBy;

    @Column(name = "ModifiedOn")
    private Date modifiedOn;

    @Column(name = "ModifiedBy")
    private String modifiedBy;
}
