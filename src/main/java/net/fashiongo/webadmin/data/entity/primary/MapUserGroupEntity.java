package net.fashiongo.webadmin.data.entity.primary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "[Security.Map_User_Group]")
@Getter
@Setter
public class MapUserGroupEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MapID")
	private Integer mapId;
	
	@Column(name="UserID")
	private Integer userId;
	
	@Column(name="GroupID")
	private Integer groupId;
}
