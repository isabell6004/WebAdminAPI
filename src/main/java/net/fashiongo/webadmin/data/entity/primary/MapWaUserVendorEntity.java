package net.fashiongo.webadmin.data.entity.primary;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "Map_Wa_User_Vendor")
public class MapWaUserVendorEntity {

	@Id
	@Column(name = "MapID")
	private Integer mapID;

	@Column(name = "UserID")
	private int userID;

	@Column(name = "VendorID")
	private int vendorID;
}
