package net.fashiongo.webadmin.model.pojo.ad.parameter;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
* @author Jiwon Kim
*/
public class GetFGCategoryListAdCountParameter {
    @ApiModelProperty(required = false, example="2018-01")
	@JsonProperty("categoryDate")
	private String categoryDate;
    
    @ApiModelProperty(required = false, example="1")
	@JsonProperty("Lvl")
	private Integer Lvl;
    
    @ApiModelProperty(required = false, example="1")
	@JsonProperty("categoryID")
	private Integer categoryID;
    
    @ApiModelProperty(required = false, example="31")
	@JsonProperty("lastDate")
    private Integer lastDate;

	public String getCategoryDate() {
		return categoryDate + "-01";
	}

	public void setCategoryDate(String categoryDate) {
		this.categoryDate = categoryDate;
	}

	public Integer getLvl() {
		return Lvl;
	}

	public void setLvl(Integer lvl) {
		Lvl = lvl;
	}

	public Integer getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(Integer categoryID) {
		this.categoryID = categoryID;
	}

	public Integer getLastDate() {
		return lastDate;
	}

	public void setLastDate(Integer lastDate) {
		this.lastDate = lastDate;
	}
    
    
}