package net.fashiongo.webadmin.data.entity.primary;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "List_EntityAction")
@Getter
@Setter
public class ListEntityActionEntity {
    @Id
    @Setter(AccessLevel.NONE)
    @Column(name = "EntityActionID")
    private Integer entityActionID;

    @Column(name = "EntityActionName")
    private String entityActionName;
}
