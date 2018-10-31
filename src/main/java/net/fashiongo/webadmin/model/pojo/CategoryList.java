package net.fashiongo.webadmin.model.pojo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * 
 * @author Jiwon Kim
 */
public class CategoryList {


	@JsonProperty("CollectionCategoryID")
	@Column(name = "CollectionCategoryID")
	private Integer collectionCategoryID;
	@JsonProperty("CollectionCategoryName")
	@Column(name = "CollectionCategoryName")
	private String collectionCategoryName;
	@JsonProperty("ParentCollectionCategoryID")
	@Column(name = "ParentCollectionCategoryID")
	private Integer parentCollectionCategoryID;
	@JsonProperty("SpotID")
	@Column(name = "SpotID")
	private Integer spotID;
	@JsonProperty("Lvl")
	@Column(name = "Lvl")
	private Integer lvl;
	@JsonProperty("CategoryID")
	@Column(name = "CategoryID")
	private Integer categoryID;
	@JsonProperty("ChildCount")
	@Column(name = "ChildCount")
	private Integer childCount;
	@JsonProperty("BidCount")
	@Column(name = "BidCount")
	private Integer bidCount;
	@JsonProperty("CuratedCount")
	@Column(name = "CuratedCount")
	private Integer curatedCount;
	@JsonProperty("BestCount")
	@Column(name = "BestCount")
	private Integer bestCount;
	@JsonProperty("NotCount")
	@Column(name = "NotCount")
	private Integer notCount;
	
	
	public Integer getCollectionCategoryID() {
		return collectionCategoryID;
	}
	public void setCollectionCategoryID(Integer collectionCategoryID) {
		this.collectionCategoryID = collectionCategoryID;
	}
	public String getCollectionCategoryName() {
		return collectionCategoryName;
	}
	public void setCollectionCategoryName(String collectionCategoryName) {
		this.collectionCategoryName = collectionCategoryName;
	}
	public Integer getParentCollectionCategoryID() {
		return parentCollectionCategoryID;
	}
	public void setParentCollectionCategoryID(Integer parentCollectionCategoryID) {
		this.parentCollectionCategoryID = parentCollectionCategoryID;
	}
	public Integer getSpotID() {
		return spotID;
	}
	public void setSpotID(Integer spotID) {
		this.spotID = spotID;
	}
	public Integer getLvl() {
		return lvl;
	}
	public void setLvl(Integer lvl) {
		this.lvl = lvl;
	}
	public Integer getCategoryID() {
		return categoryID;
	}
	public void setCategoryID(Integer categoryID) {
		this.categoryID = categoryID;
	}
	public Integer getChildCount() {
		return childCount;
	}
	public void setChildCount(Integer childCount) {
		this.childCount = childCount;
	}
	public Integer getBidCount() {
		return bidCount;
	}
	public void setBidCount(Integer bidCount) {
		this.bidCount = bidCount;
	}
	public Integer getCuratedCount() {
		return curatedCount;
	}
	public void setCuratedCount(Integer curatedCount) {
		this.curatedCount = curatedCount;
	}
	public Integer getBestCount() {
		return bestCount;
	}
	public void setBestCount(Integer bestCount) {
		this.bestCount = bestCount;
	}
	public Integer getNotCount() {
		return notCount;
	}
	public void setNotCount(Integer notCount) {
		this.notCount = notCount;
	}
	
	
}