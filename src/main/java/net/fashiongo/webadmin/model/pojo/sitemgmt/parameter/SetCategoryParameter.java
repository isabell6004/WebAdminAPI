package net.fashiongo.webadmin.model.pojo.sitemgmt.parameter;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
import net.fashiongo.webadmin.model.primary.Category;

/**
 * @author Nayeon Kim
 */
public class SetCategoryParameter  {
	@ApiModelProperty(required = false, example = "Act")
	private String settype;

	@JsonProperty("obj")
	private Category objCategory;

	public String getSettype() {
		return StringUtils.isEmpty(settype) ? null : settype;
	}

	public void setSettype(String settype) {
		this.settype = settype;
	}

	public Category getObjCategory() {
		return objCategory;
	}

	public void setObjCategory(Category objCategory) {
		this.objCategory = objCategory;
	}
}
