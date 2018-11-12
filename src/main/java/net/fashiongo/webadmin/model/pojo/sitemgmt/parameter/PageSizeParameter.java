package net.fashiongo.webadmin.model.pojo.sitemgmt.parameter;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
/**
 * 
 * @author DAHYE
 *
 */
public class PageSizeParameter {
	 @ApiModelProperty(required = false, example="1")
	 @JsonProperty("pagenum")
	 private Integer pageNum;
	 
	 @ApiModelProperty(required = false, example="15")
	 @JsonProperty("pagesize")
	 protected Integer pageSize;

	public Integer getPageNum() {
		return pageNum == null ? 1 : pageNum;
	}

	public Integer getPageSize() {
		return pageSize == null ? 10: pageSize;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
	 
}
