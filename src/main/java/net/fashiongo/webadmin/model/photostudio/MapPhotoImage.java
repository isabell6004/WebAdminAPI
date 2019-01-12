package net.fashiongo.webadmin.model.photostudio;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import net.fashiongo.common.dal.IPersistent;

@Entity
@Table(name = "Map_Photo_Image")
public class MapPhotoImage  implements IPersistent, Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ImageMappingID")
	private Integer imageMappingID;
	public Integer getImageMappingID() {
		return imageMappingID;
	}

	public void setImageMappingID(Integer imageMappingID) {
		this.imageMappingID = imageMappingID;
	}

	@Column(name = "MappingType")
	private Integer mappingType;
	public Integer getMappingType() {
		return mappingType;
	}

	public void setMappingType(Integer mappingType) {
		this.mappingType = mappingType;
	}

	@Column(name = "ImageID")
	private Integer imageID;
	public Integer getImageID() {
		return imageID;
	}

	public void setImageID(Integer imageID) {
		this.imageID = imageID;
	}

	@Column(name = "ReferenceID")
	private Integer referenceID;
	public Integer getReferenceID() {
		return referenceID;
	}

	public void setReferenceID(Integer referenceID) {
		this.referenceID = referenceID;
	}

	@Column(name = "ListOrder")
	private Integer listOrder;
	public Integer getListOrder() {
		return listOrder;
	}

	public void setListOrder(Integer listOrder) {
		this.listOrder = listOrder;
	}

	@Column(name = "ColorID")
	private Integer colorID;
	public Integer getColorID() {
		return colorID;
	}

	public void setColorID(Integer colorID) {
		this.colorID = colorID;
	}
}
