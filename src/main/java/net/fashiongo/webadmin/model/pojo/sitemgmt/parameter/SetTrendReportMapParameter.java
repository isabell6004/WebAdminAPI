package net.fashiongo.webadmin.model.pojo.sitemgmt.parameter;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

public class SetTrendReportMapParameter {
	@ApiModelProperty(required = false, example = "Del/Add")
	@JsonProperty("settype")
	public String setType;

	@ApiModelProperty(required = false, example = "1")
	@JsonProperty("trendreportid")
	public int trendreportId;

	@ApiModelProperty(required = false, example = "1")
	@JsonProperty("productid")
	public int productId;

	@ApiModelProperty(required = false, example = "1")
	@JsonProperty("mapid")
	public int mapId;

	@ApiModelProperty(required = false, example = "")
	@JsonProperty("modifiedby")
	public String modifiedBy;

	public String getSetType() {
		return setType;
	}

	public void setSetType(String setType) {
		this.setType = setType;
	}

	public int getTrendreportId() {
		return trendreportId;
	}

	public void setTrendreportId(int trendreportId) {
		this.trendreportId = trendreportId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getMapId() {
		return mapId;
	}

	public void setMapId(int mapId) {
		this.mapId = mapId;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

}
