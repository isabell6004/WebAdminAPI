package net.fashiongo.webadmin.model.pojo.parameter;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

public class SetTrendReportSortParameter {
	@ApiModelProperty(required = false, example = "")
	@JsonProperty("XMLDatas")
	public String xMLDatas;

	public String getxMLDatas() {
		return StringUtils.isEmpty(xMLDatas) ? "" : xMLDatas;
	}

	public void setxMLDatas(String xMLDatas) {
		this.xMLDatas = xMLDatas;
	}
}
