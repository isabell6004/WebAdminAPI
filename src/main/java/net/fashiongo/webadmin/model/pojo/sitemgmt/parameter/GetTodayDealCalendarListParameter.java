package net.fashiongo.webadmin.model.pojo.sitemgmt.parameter;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;

import io.swagger.annotations.ApiModelProperty;

public class GetTodayDealCalendarListParameter implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(required = false, example="9/14/2018")
	private String selectdate;
	
	@ApiModelProperty(required = false, example="0")
	private Integer wholesalerid;
	
	public Date getSelectdate(){
		SimpleDateFormat dt = new SimpleDateFormat("MM/d/yyyy",Locale.US);
		try {
			return StringUtils.isNotEmpty(this.selectdate) ? dt.parse(this.selectdate) : null;
		} catch (ParseException e) {
			return null;
		}
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