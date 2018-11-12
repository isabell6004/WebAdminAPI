package net.fashiongo.webadmin.model.pojo.sitemgmt.parameter;

import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @author Incheol Jung
 */
public class GetVendorListParameter {
	@ApiModelProperty(required = false, example = "1")
	private String categoryid;

	public String getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(String categoryid) {
		this.categoryid = categoryid;
	}
	
}
