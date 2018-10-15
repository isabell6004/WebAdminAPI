package net.fashiongo.webadmin.model.pojo.parameter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author Nayeon Kim
 */
public class GetSecurityLogsParameter {
	@ApiModelProperty(required = false, example="1")
	private Integer pageNum;
	
	@ApiModelProperty(required = false, example="30")
	private Integer pageSize;
	
	@ApiModelProperty(required = false, example="ID")
	private String sortField;
	
	@ApiModelProperty(required = false, example="desc")
	private String sortDir;
	
	@ApiModelProperty(required = false, example="165")
	private Integer usrId;
	
	@ApiModelProperty(required = false, example="1")
	private Integer periodType;
	
	@ApiModelProperty(required = false, example="08/01/2018")
	private String sDate;
	
	@ApiModelProperty(required = false, example="08/31/2018")
	private String eDate;	
	
	@ApiModelProperty(required = false, example="165.225.39.73")
	private String ip;

	public Integer getPageNum() {
		return pageNum == null ? 0 : pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return pageSize == null ? 0 : pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getSortField() {
		return sortField;
	}

	public void setSortField(String sortField) {
		this.sortField = sortField;
	}

	public String getSortDir() {
		return sortDir;
	}

	public void setSortDir(String sortDir) {
		this.sortDir = sortDir;
	}

	public Integer getUsrId() {
		return (this.usrId != null && this.usrId.equals(0)) ? null : this.usrId;
	}

	public void setUsrId(Integer usrId) {
		this.usrId = usrId;
	}

	public Integer getPeriodType() {
		return periodType == null ? 0 : periodType;
	}

	public void setPeriodType(Integer periodType) {
		this.periodType = periodType;
	}

	public String getsDate() {
		return sDate;
	}

	public void setsDate(String sDate) {
		this.sDate = sDate;
	}

	public String geteDate() {
		return eDate;
	}

	public void seteDate(String eDate) {
		this.eDate = eDate;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@JsonIgnore
	public Date getStartDate() {
		Date startDate = new Date();
		SimpleDateFormat dt = new SimpleDateFormat("MM/dd/yyyy");
		try {
			startDate= dt.parse(sDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return startDate;
	}

	@JsonIgnore
	public Date getEndDate() {
		Date endDate = new Date();
		SimpleDateFormat dt = new SimpleDateFormat("MM/dd/yyyy");
		try {
			endDate= dt.parse(eDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return endDate;
	}
}
