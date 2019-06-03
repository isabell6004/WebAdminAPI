package net.fashiongo.webadmin.model.photostudio;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@Table(name = "Photo_Dropper")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class PhotoDropper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DropperID")
    private int dropperId;

    @Column(name = "WholeSalerID")
    private int wholeSalerId;

    @Column(name = "DropperName")
    private String dropperName;

    @Column(name = "DropperEmail")
    private String dropperEmail;

    @Column(name = "CreatedOn")
    private LocalDateTime createdOn;

    @Column(name = "CreatedBy")
    private String createdBy;

    @Column(name = "ModifiedOn")
    private LocalDateTime modifiedOn;

    @Column(name = "ModifiedBy")
    private String modifiedBy;
}
