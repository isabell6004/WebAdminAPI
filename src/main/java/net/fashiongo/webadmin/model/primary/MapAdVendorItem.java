package net.fashiongo.webadmin.model.primary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * @author Jiwon Kim
 */
@Entity
@Table(name = "[Map_AdVendor_Item]")
public class MapAdVendorItem {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "MapID")
	@JsonProperty("MapID")
	private Integer mapID;
	@Column(name = "AdID")
	@JsonProperty("AdID")
	private Integer adID;
	@Column(name = "ProductID")
	@JsonProperty("ProductID")
	private Integer productID;
	@Column(name = "ListOrder")
	@JsonProperty("ListOrder")
	private Integer listOrder;
	
	public Integer getMapID() {
		return mapID;
	}
	public void setMapID(Integer mapID) {
		this.mapID = mapID;
	}
	public Integer getAdID() {
		return adID;
	}
	public void setAdID(Integer adID) {
		this.adID = adID;
	}
	public Integer getProductID() {
		return productID;
	}
	public void setProductID(Integer productID) {
		this.productID = productID;
	}
	public Integer getListOrder() {
		return listOrder;
	}
	public void setListOrder(Integer listOrder) {
		this.listOrder = listOrder;
	}

}
