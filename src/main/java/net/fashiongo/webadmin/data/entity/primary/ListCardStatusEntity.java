package net.fashiongo.webadmin.data.entity.primary;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "List_CardStatus")
@Getter
@Setter
public class ListCardStatusEntity {
    @Id
    @Column(name = "CardStatusID")
    private Integer cardStatusID;

    @Column(name = "CardStatusName")
    private String cardStatusName;
}
