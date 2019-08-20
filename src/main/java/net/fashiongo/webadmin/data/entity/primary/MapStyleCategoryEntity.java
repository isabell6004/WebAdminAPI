package net.fashiongo.webadmin.data.entity.primary;

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
@Table(name = "Map_Style_Category")
@Getter
@Setter
public class MapStyleCategoryEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MapID")
	@Setter(AccessLevel.NONE)
	private Integer mapID;

	@Column(name = "StyleID")
	private Integer styleID;

	@Column(name = "CategoryID")
	private Integer categoryID;
}
