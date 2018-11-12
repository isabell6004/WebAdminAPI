package net.fashiongo.webadmin.model.pojo.sitemgmt.parameter;

import org.apache.commons.lang3.StringUtils;

import io.swagger.annotations.ApiModelProperty;

public class StartDateParameter {
	@ApiModelProperty(required = false, example="2018-10")
	private String sDate;

	public String getsDate() {
		return StringUtils.isEmpty(sDate) ? "" : sDate;
	}

	public void setsDate(String sDate) {
		this.sDate = sDate;
	}
}
