package net.fashiongo.webadmin.data.entity.primary;

import lombok.AccessLevel;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * 
 * @author Reo
 *
 */
@Entity
@Table(name = "Map_Length_Category")
public class MapLengthCategoryEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MapID")
	@Setter(AccessLevel.NONE)
	private Integer mapID;

	@Column(name = "LengthID")
	private Integer lengthID;

	@Column(name = "CategoryID")
	private Integer categoryID;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "CategoryID",referencedColumnName = "CategoryID",insertable = false,updatable = false)
	private List<CategoryEntity> categoryEntityList;
}
