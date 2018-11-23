package net.fashiongo.webadmin.model.primary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author Reo
 *
 */
@Entity
@Table(name = "MessageCategory")
public class MessageCategory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("MessageCategoryID")
	@Column(name = "MessageCategoryID")
	private Integer messageCategoryID;
	
	@JsonProperty("CategoryName")
	@Column(name = "CategoryName")
	private String categoryName;
	
	@JsonProperty("IsForRetailer")
	@Column(name = "IsForRetailer")
	private Boolean isForRetailer;
	
	@JsonProperty("IsForWholeSaler")
	@Column(name = "IsForWholeSaler")
	private Boolean isForWholeSaler;
	
	@JsonProperty("IsForFashiongo")
	@Column(name = "IsForFashiongo")
	private Boolean isForFashiongo;
	
	@JsonProperty("Active")
	@Column(name = "Active")
	private Boolean active;
	
	@JsonProperty("SortNoRetailer")
	@Column(name = "SortNoRetailer")
	private Integer sortNoRetailer;
	
	@JsonProperty("SortNoWholeSaler")
	@Column(name = "SortNoWholeSaler")
	private Integer sortNoWholeSaler;
	
	@JsonProperty("SortNoFashiongo")
	@Column(name = "SortNoFashiongo")
	private Integer sortNoFashiongo;

	public Integer getMessageCategoryID() {
		return messageCategoryID;
	}

	public void setMessageCategoryID(Integer messageCategoryID) {
		this.messageCategoryID = messageCategoryID;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Boolean getIsForRetailer() {
		return isForRetailer;
	}

	public void setIsForRetailer(Boolean isForRetailer) {
		this.isForRetailer = isForRetailer;
	}

	public Boolean getIsForWholeSaler() {
		return isForWholeSaler;
	}

	public void setIsForWholeSaler(Boolean isForWholeSaler) {
		this.isForWholeSaler = isForWholeSaler;
	}

	public Boolean getIsForFashiongo() {
		return isForFashiongo;
	}

	public void setIsForFashiongo(Boolean isForFashiongo) {
		this.isForFashiongo = isForFashiongo;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Integer getSortNoRetailer() {
		return sortNoRetailer;
	}

	public void setSortNoRetailer(Integer sortNoRetailer) {
		this.sortNoRetailer = sortNoRetailer;
	}

	public Integer getSortNoWholeSaler() {
		return sortNoWholeSaler;
	}

	public void setSortNoWholeSaler(Integer sortNoWholeSaler) {
		this.sortNoWholeSaler = sortNoWholeSaler;
	}

	public Integer getSortNoFashiongo() {
		return sortNoFashiongo;
	}

	public void setSortNoFashiongo(Integer sortNoFashiongo) {
		this.sortNoFashiongo = sortNoFashiongo;
	}
	
	
}
