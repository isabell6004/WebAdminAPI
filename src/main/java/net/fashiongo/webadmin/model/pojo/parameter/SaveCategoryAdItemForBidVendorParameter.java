package net.fashiongo.webadmin.model.pojo.parameter;

import java.util.List;

import javax.persistence.Column;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
import net.fashiongo.webadmin.model.primary.MapAdVendorItem;

/**
* @author Jiwon Kim
*/
public class SaveCategoryAdItemForBidVendorParameter {
    
	
    @ApiModelProperty(required = false, example="")
	@JsonProperty("AdID")
	@Column(name = "AdID")
	private String adID;
    @ApiModelProperty(required = false, example="")
	@JsonProperty("VendorCategoryID")
	@Column(name = "VendorCategoryID")
	private String vendorCategoryID;
    @ApiModelProperty(required = false, example="")
    @JsonProperty("mapadvendoritem")
	private List<MapAdVendorItem> mapAdVendorItem;
    
    
	public Integer getAdID() {
        return StringUtils.isEmpty(adID) ? 0 : Integer.parseInt(adID);
	}
	public Integer getVendorCategoryID() {
        return StringUtils.isEmpty(vendorCategoryID) ? 0 : Integer.parseInt(vendorCategoryID);
	}
    public List<MapAdVendorItem> getMapAdVendorItem() {
		return mapAdVendorItem;
	}
	public void setMapAdVendorItem(List<MapAdVendorItem> mapAdVendorItem) {
		this.mapAdVendorItem = mapAdVendorItem;
	}

    
    

}