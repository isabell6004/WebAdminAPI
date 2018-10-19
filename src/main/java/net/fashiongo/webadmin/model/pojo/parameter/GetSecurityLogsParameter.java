package net.fashiongo.webadmin.model.pojo.parameter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author Nayeon Kim
 */
public class GetSecurityLogsParameter {
	@ApiModelProperty(required = false, example="1")
	private Integer pagenum;
	
	@ApiModelProperty(required = false, example="30")
	private Integer pagesize;
	
	@ApiModelProperty(required = false, example="ID")
	private String sortfield;
	
	@ApiModelProperty(required = false, example="desc")
	private String sortdir;
	
	@ApiModelProperty(required = false, example="165")
	private Integer usrid;
	
	@ApiModelProperty(required = false, example="1")
	private Integer periodtype;
	
	@ApiModelProperty(required = false, example="08/01/2018")
	private String sdate;
	
	@ApiModelProperty(required = false, example="08/31/2018")
	private String edate;	
	
	@ApiModelProperty(required = false, example="165.225.39.73")
	private String ip;

	public Integer getPagenum() {
		return pagenum == null ? 0 : pagenum;
	}
	
	public Integer getPagesize() {
		return pagesize == null ? 0 : pagesize;
	}

	public String getSortfield() {
		return sortfield;
	}

	public String getSortdir() {
		return sortdir;
	}

	public Integer getUsrid() {
		return (this.usrid != null && this.usrid.equals(0)) ? null : this.usrid;
	}

	public Integer getPeriodtype() {
		return periodtype == null ? 0 : periodtype;
	}

	public String getSdate() {
		return sdate;
	}

	public String getEdate() {
		return edate;
	}

	public String getIp() {
		return ip;
	}

	@JsonIgnore
	public Date getStartDate() {
		Date startDate = new Date();
		SimpleDateFormat dt = new SimpleDateFormat("MM/dd/yyyy");
		try {
			startDate= StringUtils.isEmpty(sdate) ? null : dt.parse(sdate);
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
			endDate= StringUtils.isEmpty(edate) ? null : dt.parse(edate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return endDate;
	}

	public void setPagenum(Integer pagenum) {
		this.pagenum = pagenum;
	}

	public void setPagesize(Integer pagesize) {
		this.pagesize = pagesize;
	}

	public void setSortfield(String sortfield) {
		this.sortfield = sortfield;
	}

	public void setSortdir(String sortdir) {
		this.sortdir = sortdir;
	}

	public void setUsrid(Integer usrid) {
		this.usrid = usrid;
	}

	public void setPeriodtype(Integer periodtype) {
		this.periodtype = periodtype;
	}

	public void setSdate(String sdate) {
		this.sdate = sdate;
	}

	public void setEdate(String edate) {
		this.edate = edate;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
}
