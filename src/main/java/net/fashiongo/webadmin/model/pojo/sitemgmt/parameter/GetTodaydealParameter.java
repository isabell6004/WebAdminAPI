package net.fashiongo.webadmin.model.pojo.sitemgmt.parameter;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;

import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @author Incheol Jung
 */
public class GetTodaydealParameter implements Serializable{
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(required = false, example="1")
	private String pagenum;
	@ApiModelProperty(required = false, example="20")
	private String pagesize;
	@ApiModelProperty(required = false, example="2858")
	private String wholesalerid;
	@ApiModelProperty(required = false, example="true")
	private String active;
	@ApiModelProperty(required = false, example="100")
	private String categoryid;
	@ApiModelProperty(required = false, example="true")
	private String companytypeid1;
	@ApiModelProperty(required = false, example="false")
	private String companytypeid2;
	@ApiModelProperty(required = false, example="true")
	private String companytypeid3;
	@ApiModelProperty(required = false, example="10/1/2018, 12:00:00 AM")
	private String fromdate;
	@ApiModelProperty(required = false, example="10/31/2018, 11:59:59 PM")
	private String todate;
	@ApiModelProperty(required = false, example="CategoryID")
	private String orderby;
	
	
	public Integer getPagenum() {
		return StringUtils.isEmpty(this.pagenum) ? 1 : Integer.parseInt(this.pagenum);
	}
	public void setPagenum(String pagenum) {
		this.pagenum = pagenum;
	}
	public Integer getPagesize() {
		return StringUtils.isEmpty(this.pagesize) ? 10 : Integer.parseInt(this.pagesize);
	}
	public void setPagesize(String pagesize) {
		this.pagesize = pagesize;
	}
	public Integer getWholesalerid() {
		return StringUtils.isEmpty(this.wholesalerid) ? 0 : Integer.parseInt(this.wholesalerid);
	}
	public void setWholesalerid(String wholesalerid) {
		this.wholesalerid = wholesalerid;
	}
	public Boolean getActive() {
		return StringUtils.isEmpty(this.active) ? null : Boolean.parseBoolean(this.active);
	}
	public void setActive(String active) {
		this.active = active;
	}
	public Integer getCategoryid() {
		return StringUtils.isEmpty(this.categoryid) ? 100 : Integer.parseInt(this.categoryid);
	}
	public void setCategoryid(String categoryid) {
		this.categoryid = categoryid;
	}
	public boolean getCompanytypeid1() {
		return StringUtils.isEmpty(this.companytypeid1) ? false : Boolean.parseBoolean(this.companytypeid1);
	}
	public void setCompanytypeid1(String companytypeid1) {
		this.companytypeid1 = companytypeid1;
	}
	public boolean getCompanytypeid2() {
		return StringUtils.isEmpty(this.companytypeid2) ? false : Boolean.parseBoolean(this.companytypeid2);
	}
	public void setCompanytypeid2(String companytypeid2) {
		this.companytypeid2 = companytypeid2;
	}
	public boolean getCompanytypeid3() {
		return StringUtils.isEmpty(this.companytypeid3) ? false : Boolean.parseBoolean(this.companytypeid3);
	}
	public void setCompanytypeid3(String companytypeid3) {
		this.companytypeid3 = companytypeid3;
	}
	
	@Transient
	public String getCheckedCompanyNo() {
		String checkedCompanyNo = null;
		
		if (this.getCompanytypeid1() == true)
        {
			checkedCompanyNo = "2,";
        }
        if (this.getCompanytypeid2() == true)
        {
        	checkedCompanyNo = checkedCompanyNo.concat("1,");
        }
        if (this.getCompanytypeid3() == true)
        {
        	checkedCompanyNo = checkedCompanyNo.concat("3,");
        }
        if (this.getCompanytypeid1() == true || this.getCompanytypeid2() == true || this.getCompanytypeid3() == true)
        {
        	checkedCompanyNo = checkedCompanyNo.substring(0, checkedCompanyNo.length()-1);
        }
		
		return checkedCompanyNo;
	}
	
	public Date getFromdate() {
		SimpleDateFormat dt = new SimpleDateFormat("MM/d/yyyy, hh:mm:ss a",Locale.US);
		try {
			return (StringUtils.isNotEmpty(this.fromdate) && !this.fromdate.equals("Invalid Date")) ?dt.parse(this.fromdate) : null;
		} catch (ParseException e) {
			return null;
		}
	}
	public void setFromdate(String fromdate) {
		this.fromdate = fromdate;
	}
	public Date getTodate() {
		SimpleDateFormat dt = new SimpleDateFormat("MM/d/yyyy, hh:mm:ss a",Locale.US);
		try {
			return (StringUtils.isNotEmpty(this.todate) && !this.todate.equals("Invalid Date")) ? dt.parse(this.todate) : null;
		} catch (ParseException e) {
			return null;
		}
	}
	public void setTodate(String todate) {
		this.todate = todate;
	}
	public String getOrderby() {
		return StringUtils.isEmpty(this.orderby) ? null : this.orderby;
	}
	public void setOrderby(String orderby) {
		this.orderby = orderby;
	}
	
}