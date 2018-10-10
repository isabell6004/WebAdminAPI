package net.fashiongo.webadmin.model.pojo.parameter;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import io.swagger.annotations.ApiModelProperty;

public class GetBidSettingLastRecordsParameter {
	
	@ApiModelProperty(required = false, example="0")
	private Integer spotId;
	
	@ApiModelProperty(required = false, example="201610")
	private String mth;
	
	@ApiModelProperty(required = false, example="0")
	private Integer weekDay;
	
	@ApiModelProperty(required = false, example="8/1/2018")
	private String fromDate;
	
	@ApiModelProperty(required = false, example="8/31/2018")
	private String toDate;
	
	public Integer getSpotId() {
		return spotId;
	}
	public void setSpotId(Integer spotId) {
		this.spotId = spotId;
	}
	public String getMth() {
		return mth;
	}
	public void setMth(String mth) {
		this.mth = mth;
	}
	public Integer getWeekDay() {
		return weekDay;
	}
	public void setWeekDay(Integer weekDay) {
		this.weekDay = weekDay;
	}

	public String getFromDate() {
		SimpleDateFormat convertDate = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat convertString = new SimpleDateFormat("yyyyMMdd");
		try {
			fromDate = convertString.format(convertDate.parse(fromDate));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		SimpleDateFormat convertDate = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat convertString = new SimpleDateFormat("yyyyMMdd");
		try {
			toDate = convertString.format(convertDate.parse(toDate));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	
}
