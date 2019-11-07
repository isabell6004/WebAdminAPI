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
@Table(name = "tblShipMethod")
public class ShipMethod {
    @Id @Column(name = "ShipMethodID", nullable = false) private int id;
    @Column(name = "CourierID") private Integer courierId;
    @Column(name = "ShipMethodName", length = 30) private String shipMethodName;
    @Column(name = "StartingDate") private LocalDateTime startingDate;
    @Column(name = "Active") private Boolean active;
    @Column(name = "LastUser", length = 50) private String lastUser;
    @Column(name = "LastModifiedDateTime") private LocalDateTime lastModifiedDateTime;
}
