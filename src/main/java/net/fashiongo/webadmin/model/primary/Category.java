package net.fashiongo.webadmin.model.primary;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.fashiongo.common.conversion.LocalDateTimeConverter;
import net.fashiongo.webadmin.converter.HtmlEscapeConverter;

/**
 *
 * @author sanghyup
 */
@Entity
@Table(name = "Category")
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Basic(optional = false)
	//@NotNull
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@JsonProperty("CategoryID")
	@Column(name = "CategoryID")
	private Integer categoryID;

	@JsonProperty("CategoryName")
	@Column(name = "CategoryName")
	private String categoryName;

	@Transient
	@JsonProperty("CategoryName2")
	@Column(name = "CategoryName2")
	private String categoryName2;

	@JsonProperty("CategoryDescription")
	@Column(name = "CategoryDescription")
	private String categoryDescription;

	@JsonProperty("ParentCategoryID")
	@Column(name = "ParentCategoryID")
	private Integer parentCategoryID;

	@JsonProperty("ParentParentCategoryID")
	@Column(name = "ParentParentCategoryID")
	private Integer parentParentCategoryID;

	@JsonProperty("Lvl")
	@Column(name = "Lvl")
	private Integer lvl;

	@JsonProperty("TitleImage")
	@Column(name = "TitleImage")
	private String titleImage;

	@JsonProperty("IsLandingPage")
	@Column(name = "IsLandingPage")
	private Boolean isLandingPage;

	@JsonProperty("IsFeatured")
	@Column(name = "IsFeatured")
	private Boolean isFeatured;

	@JsonProperty("ListOrder")
	@Column(name = "ListOrder")
	private Integer listOrder;

	@JsonProperty("Active")
	@Column(name = "Active")
	private Boolean active;

	public Integer getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(Integer categoryID) {
		this.categoryID = categoryID;
	}

	public String getCategoryName() {
		return categoryName;
	}

	@Convert(converter = HtmlEscapeConverter.class)
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryName2() {
		return categoryName2;
	}

	@Convert(converter = HtmlEscapeConverter.class)
	public void setCategoryName2(String categoryName2) {
		this.categoryName2 = categoryName2;
	}

	public String getCategoryDescription() {
		return categoryDescription;
	}

	@Convert(converter = HtmlEscapeConverter.class)
	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}

	public Integer getParentCategoryID() {
		return parentCategoryID;
	}

	public void setParentCategoryID(Integer parentCategoryID) {
		this.parentCategoryID = parentCategoryID;
	}

	public Integer getParentParentCategoryID() {
		return parentParentCategoryID;
	}

	public void setParentParentCategoryID(Integer parentParentCategoryID) {
		this.parentParentCategoryID = parentParentCategoryID;
	}

	public Integer getLvl() {
		return lvl;
	}

	public void setLvl(Integer lvl) {
		this.lvl = lvl;
	}

	public String getTitleImage() {
		return titleImage;
	}

	public void setTitleImage(String titleImage) {
		this.titleImage = titleImage;
	}

	public Boolean getIsLandingPage() {
		return isLandingPage;
	}

	public void setIsLandingPage(Boolean isLandingPage) {
		this.isLandingPage = isLandingPage;
	}

	public Boolean getIsFeatured() {
		return isFeatured;
	}

	public void setIsFeatured(Boolean isFeatured) {
		this.isFeatured = isFeatured;
	}

	public Integer getListOrder() {
		return listOrder;
	}

	public void setListOrder(Integer listOrder) {
		this.listOrder = listOrder;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
