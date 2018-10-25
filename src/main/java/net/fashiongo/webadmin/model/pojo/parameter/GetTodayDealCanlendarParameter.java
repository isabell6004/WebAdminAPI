package net.fashiongo.webadmin.model.pojo.parameter;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;

import io.swagger.annotations.ApiModelProperty;

public class GetTodayDealCanlendarParameter implements Serializable {
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(required = false, example="2018-9-1")
	private String fromdate;
	@ApiModelProperty(required = false, example="2018-9-30")
	private String todate;
	
	public String getFromdate() {
//		DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("yyyy-M-d");
//		return StringUtils.isNotEmpty(this.fromdate) ? LocalDateTime.now().minusDays(20) : null;
		return this.fromdate;
	}
	public void setFromdate(String fromdate) {
		this.fromdate = fromdate;
		
	}
	public String getTodate() {
//		DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("yyyy-M-d");
//		return StringUtils.isNotEmpty(this.fromdate) ? LocalDateTime.now() : null;
		return this.todate;
	}
	public void setTodate(String todate) {
		this.todate = todate;
	}
}