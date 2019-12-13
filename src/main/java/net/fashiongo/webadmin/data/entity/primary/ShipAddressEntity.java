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
@Table(name = "XShipAddress")
public class ShipAddressEntity {
	@Id @Column(name = "ShipAddID") Integer id;
	@Column(name = "VerifiedOn") LocalDateTime verifiedOn;
}
