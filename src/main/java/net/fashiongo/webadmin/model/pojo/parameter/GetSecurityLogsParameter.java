package net.fashiongo.webadmin.model.pojo.parameter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author Nayeon Kim
 */
public class GetSecurityLogsParameter {
	@ApiModelProperty(required = false, example="1")
	@JsonProperty("pagenum")
	private Integer pageNum;
	
	@ApiModelProperty(required = false, example="30")
	@JsonProperty("pagesize")
	private Integer pageSize;
	
	@ApiModelProperty(required = false, example="ID")
	@JsonProperty("sortfield")
	private String sortField;
	
	@ApiModelProperty(required = false, example="desc")
	@JsonProperty("sortdir")
	private String sortDir;
	
	@ApiModelProperty(required = false, example="165")
	@JsonProperty("usrid")
	private Integer usrId;
	
	@ApiModelProperty(required = false, example="1")
	@JsonProperty("periodtype")
	private Integer periodType;
	
	@ApiModelProperty(required = false, example="08/01/2018")
	@JsonProperty("sdate")
	private String sDate;
	
	@ApiModelProperty(required = false, example="08/31/2018")
	@JsonProperty("eDate")
	private String eDate;	
	
	@ApiModelProperty(required = false, example="165.225.39.73")
	@JsonProperty("ip")
	private String ip;

	@ApiModelProperty(hidden = true) 
	private Date startDate;
	
	@ApiModelProperty(hidden = true) 
	private Date endDate;
	
	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return pageSize;
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
		return usrId;
	}

	public void setUsrId(Integer usrId) {
		this.usrId = usrId;
	}

	public Integer getPeriodType() {
		return periodType;
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

	public Date getStartDate() {
		SimpleDateFormat dt = new SimpleDateFormat("MM/dd/yyyy");
		try {
			startDate= dt.parse(sDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		SimpleDateFormat dt = new SimpleDateFormat("MM/dd/yyyy");
		try {
			endDate= dt.parse(eDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
}
