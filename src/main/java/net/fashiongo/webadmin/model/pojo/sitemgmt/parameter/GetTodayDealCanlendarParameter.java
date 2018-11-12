package net.fashiongo.webadmin.model.pojo.sitemgmt.parameter;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;

import io.swagger.annotations.ApiModelProperty;

public class GetTodayDealCanlendarParameter implements Serializable {
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(required = false, example="2018-9-1")
	private String fromdate;
	@ApiModelProperty(required = false, example="2018-9-30")
	private String todate;
	
	public Date getFromdate() {
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-d",Locale.US);
		try {
			return StringUtils.isNotEmpty(this.fromdate) ? dt.parse(this.fromdate) : null;
		} catch (ParseException e) {
			return null;
		}
	}
	public void setFromdate(String fromdate) {
		this.fromdate = fromdate;
	}
	
	public Date getTodate() {
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-d",Locale.US);
		try {
			return StringUtils.isNotEmpty(this.todate) ? dt.parse(this.todate) : null;
		} catch (ParseException e) {
			return null;
		}
	}
	public void setTodate(String todate) {
		this.todate = todate;
	}
}