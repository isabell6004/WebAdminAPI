package net.fashiongo.webadmin.model.primary;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author sanghyup
 */
@Entity
@Table(name = "CollectionCategory")
public class CollectionCategory2 implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@NotNull
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@JsonProperty("categoryId")
	@Column(name = "CollectionCategoryID")
	private Integer collectionCategoryID;

	@JsonProperty("parentCategoryId")
	@Column(name = "ParentCollectionCategoryID")
	private Integer parentCollectionCategoryID;

	@Size(max = 50)
	@JsonProperty("categoryName")
	@Column(name = "CollectionCategoryName")
	private String collectionCategoryName;

	@JsonProperty("lvl")
	@Column(name = "Lvl")
	private Integer lvl;

	@JsonProperty("listOrder")
	@Column(name = "ListOrder")
	private Integer listOrder;

	@JsonProperty("active")
	@Column(name = "Active")
	private Boolean active;

	public Integer getCollectionCategoryID() {
		return collectionCategoryID;
	}

	public void setCollectionCategoryID(Integer collectionCategoryID) {
		this.collectionCategoryID = collectionCategoryID;
	}

	public Integer getParentCollectionCategoryID() {
		return parentCollectionCategoryID;
	}

	public void setParentCollectionCategoryID(Integer parentCollectionCategoryID) {
		this.parentCollectionCategoryID = parentCollectionCategoryID;
	}

	public String getCollectionCategoryName() {
		return collectionCategoryName;
	}

	public void setCollectionCategoryName(String collectionCategoryName) {
		this.collectionCategoryName = collectionCategoryName;
	}

	public Integer getLvl() {
		return lvl;
	}

	public void setLvl(Integer lvl) {
		this.lvl = lvl;
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
