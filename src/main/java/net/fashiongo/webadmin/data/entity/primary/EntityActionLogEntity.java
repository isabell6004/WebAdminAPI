package net.fashiongo.webadmin.data.entity.primary;

import lombok.*;
import net.fashiongo.webadmin.data.model.vendor.SetVendorBlockParameter;
import net.fashiongo.webadmin.utility.Utility;

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

@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "Entity_ActionLog")
public class EntityActionLogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public static EntityActionLogEntity create(Integer entityTypeID, Integer entityId, Integer actionID) {
        return builder()
                .entityTypeID(entityTypeID)
                .entityID(entityId)
                .actionID(actionID)
                .actedOn(LocalDateTime.now())
                .actedBy(Utility.getUsername())
                .build();
    }

    public static EntityActionLogEntity create(Integer entityTypeID, Integer entityId, Integer actionID, String detailLog) {
        return builder()
                .entityTypeID(entityTypeID)
                .entityID(entityId)
                .actionID(actionID)
                .remark(detailLog)
                .actedOn(LocalDateTime.now())
                .actedBy(Utility.getUsername())
                .build();
    }

    public static EntityActionLogEntity create(Integer entityTypeID, Integer entityId, Integer actionID, String detailLog, LocalDateTime actedOn, String actedBy) {
        return builder()
                .entityTypeID(entityTypeID)
                .entityID(entityId)
                .actionID(actionID)
                .remark(detailLog)
                .actedOn(actedOn)
                .actedBy(actedBy)
                .build();
    }
}
