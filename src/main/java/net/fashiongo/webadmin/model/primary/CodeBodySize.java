package net.fashiongo.webadmin.model.primary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Nayeon Kim
 */
@Entity
@Table(name = "Code_BodySize")
public class CodeBodySize {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("BodySizeID")
	@Column(name = "BodySizeID")
	private Integer bodySizeID;
	
	@JsonProperty("BodySizeName")
	@Column(name = "BodySizeName")
	private String bodySizeName;
	
	@JsonProperty("TitleImage")
	@Column(name = "TitleImage")
	private String titleImage;
	
	@JsonProperty("CategoryID")
	@Column(name = "CategoryID")
	private Integer categoryID;
	
	@JsonProperty("Active")
	@Column(name = "Active")
	private Boolean active;

	public Integer getBodySizeID() {
		return bodySizeID;
	}

	public void setBodySizeID(Integer bodySizeID) {
		this.bodySizeID = bodySizeID;
	}

	public String getBodySizeName() {
		return bodySizeName;
	}

	public void setBodySizeName(String bodySizeName) {
		this.bodySizeName = bodySizeName;
	}

	public String getTitleImage() {
		return titleImage;
	}

	public void setTitleImage(String titleImage) {
		this.titleImage = titleImage;
	}

	public Integer getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(Integer categoryID) {
		this.categoryID = categoryID;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
}
