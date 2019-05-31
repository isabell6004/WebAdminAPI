package net.fashiongo.webadmin.model.photostudio;

import java.io.Serializable;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import net.fashiongo.common.dal.IPersistent;

@Entity
@Table(name = "Map_Photo_Image")
@Getter
@Setter
public class MapPhotoImage  implements IPersistent, Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ImageMappingID")
	private Integer imageMappingID;

	@Column(name = "MappingType")
	private Integer mappingType;

	@Column(name = "ImageID")
	private Integer imageID;

	@Column(name = "ReferenceID")
	private Integer referenceID;

	@Column(name = "ListOrder")
	private Integer listOrder;

	@Column(name = "ColorID")
	private Integer colorID;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ImageID", referencedColumnName = "ImageID", insertable = false, updatable = false)
	private PhotoImage photoImage;
}
