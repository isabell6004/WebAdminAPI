package net.fashiongo.webadmin.model.photostudio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Map_Photo_Category_Price")
public class MapPhotoCategoryPrice {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CategoryPriceMappingID")
	private Integer categoryPriceMappingID;
	public Integer getCategoryPriceMappingID() {
		return categoryPriceMappingID;
	}

	public void setCategoryPriceMappingID(Integer categoryPriceMappingID) {
		this.categoryPriceMappingID = categoryPriceMappingID;
	}

	@Column(name = "CategoryID")
	private Integer categoryID;
	public Integer getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(Integer categoryID) {
		this.categoryID = categoryID;
	}

	@Column(name = "PriceID")
	private Integer priceID;
	public Integer getPriceID() {
		return priceID;
	}

	public void setPriceID(Integer priceID) {
		this.priceID = priceID;
	}
	
	
}
