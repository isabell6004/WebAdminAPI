package net.fashiongo.webadmin.data.entity.primary;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "Entity_ActionLog")
@Getter
@Setter
public class EntityActionLogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    @Column(name = "LogID")
    private Integer logID;

    @Column(name = "EntityTypeID")
    private Integer entityTypeID;

    @Column(name = "EntityID")
    private Integer entityID;

    @Column(name = "ActionID")
    private Integer actionID;

    @Column(name = "ActedOn")
    private LocalDateTime actedOn;

    @Column(name = "ActedBy")
    private String actedBy;

    @Column(name = "Remark")
    private String remark;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ActionID", referencedColumnName = "EntityActionID", updatable = false, insertable = false)
    private ListEntityActionEntity listEntityAction;
}
