package net.fashiongo.webadmin.model.pojo.parameter;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

public class GetTodayDealCalendarListParameter implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(required = false, example="9/14/2018")
	private String selectdate;
	
	@ApiModelProperty(required = false, example="0")
	private Integer wholesalerid;
	
	public String getSelectdate() {
		return selectdate;
	}
	public void setSelectdate(String selectdate) {
		this.selectdate = selectdate;
	}
	public Integer getWholesalerid() {
		return wholesalerid;
	}
	public void setWholesalerid(Integer wholesalerid) {
		this.wholesalerid = wholesalerid;
	}
	
}