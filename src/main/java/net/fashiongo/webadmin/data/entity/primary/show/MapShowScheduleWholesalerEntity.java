package net.fashiongo.webadmin.data.entity.primary.show;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Map_ShowSchedule_WholeSaler")
@Getter
@Setter
public class MapShowScheduleWholesalerEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MapID")
	private Integer mapId;

	@Column(name = "ShowScheduleID")
	private int showScheduleId;

	@Column(name = "WholeSalerID")
	private int wholesalerId;

	@Column(name = "Booth")
	private String booth;
}
