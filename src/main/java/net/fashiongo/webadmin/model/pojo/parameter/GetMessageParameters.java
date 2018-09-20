/**
 * 
 */
package net.fashiongo.webadmin.model.pojo.parameter;

import org.apache.commons.lang3.StringUtils;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author Incheol Jung
 */
public class GetMessageParameters {
	@ApiModelProperty(required = true, example="1")
	private Integer pagenum;
	
	@ApiModelProperty(required = true, example="30")
	private Integer pagesize;
	
	private Integer parent;
	
	private Integer recipienttypeid;
	
	@ApiModelProperty(required = true, example="1")
	private Integer sendertypeid;
	
	private String sender;
	private Integer topic;
	private String subject;
	private String period;
	private String fromdate;
	private String todate;
	private String status;
	
	public Integer getPagenum() {
		return pagenum;
	}
	public void setPagenum(Integer pagenum) {
		this.pagenum = pagenum;
	}
	public Integer getPagesize() {
		return pagesize;
	}
	public void setPagesize(Integer pagesize) {
		this.pagesize = pagesize;
	}
	public Integer getParent() {
		return parent;
	}
	public void setParent(Integer parent) {
		this.parent = parent;
	}
	public Integer getRecipienttypeid() {
		return recipienttypeid;
	}
	public void setRecipienttypeid(Integer recipienttypeid) {
		this.recipienttypeid = recipienttypeid;
	}
	public Integer getSendertypeid() {
		return sendertypeid;
	}
	public void setSendertypeid(Integer sendertypeid) {
		this.sendertypeid = sendertypeid;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public Integer getTopic() {
		return topic;
	}
	public void setTopic(Integer topic) {
		this.topic = topic;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public String getFromdate() {
		return StringUtils.isEmpty(fromdate) ? null : fromdate;
	}
	public void setFromdate(String fromdate) {
		this.fromdate = fromdate;
	}
	public String getTodate() {
		return StringUtils.isEmpty(todate) ? null : todate;
	}
	public void setTodate(String todate) {
		this.todate = todate;
	}
	public String getStatus() {
		return StringUtils.isEmpty(status) ? null : status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
