package net.fashiongo.webadmin.model.pojo.parameter;

import java.io.Serializable;

public class GetTodayDealCalendarListParameter implements Serializable{
	private static final long serialVersionUID = 1L;
	private String selectdate;
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
