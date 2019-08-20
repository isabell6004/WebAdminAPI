package net.fashiongo.webadmin.data.entity.primary;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * 
 * @author Reo
 *
 */
@Entity
@Table(name = "Map_Fabric_Category")
@Getter
@Setter
public class MapFabricCategoryEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Setter(AccessLevel.NONE)
	@Column(name = "MapID")
	private Integer mapID;

	@Column(name = "FabricID")
	private Integer fabricID;
	
	@Column(name = "CategoryID")
	private Integer categoryID;

}
