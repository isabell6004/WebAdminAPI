package net.fashiongo.webadmin.model.pojo.parameter;

import org.apache.commons.lang3.StringUtils;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author Nayeon Kim
 */
public class GetFeaturedItemCountParameter {
	@ApiModelProperty(required = false, example="2018-10")
	private String sDate;

	public String getsDate() {
		return StringUtils.isEmpty(sDate) ? "" : sDate;
	}

	public void setsDate(String sDate) {
		this.sDate = sDate;
	}
}
