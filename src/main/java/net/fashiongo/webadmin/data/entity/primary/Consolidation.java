package net.fashiongo.webadmin.data.entity.primary;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "Consolidation")
public class Consolidation {
    @Id @Column(name = "ConsolidationID") private int id;
    @Column(name = "InhouseMemo") private String inhouseMemo;
    @Column(name = "ModifiedOn") private LocalDateTime modifiedOn;
    @Column(name = "ModifiedBy", length = 50) private String modifiedBy;
}
