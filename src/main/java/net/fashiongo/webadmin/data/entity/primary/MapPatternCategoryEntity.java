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
@Table(name = "Map_Pattern_Category")
@Getter
@Setter
public class MapPatternCategoryEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MapID")
	@Setter(AccessLevel.NONE)
	private Integer mapID;

	@Column(name = "PatternID")
	private Integer patternID;

	@Column(name = "CategoryID")
	private Integer categoryID;
}
