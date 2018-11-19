package net.fashiongo.webadmin.model.pojo.message.parameter;

import java.time.LocalDateTime;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author Reo
 *
 */
public class GetContactUsParameter {
	@JsonProperty("pagesize")
	private Integer pageSize;
	
	@JsonProperty("pagenum")
	private Integer pageNum;
	
	@JsonProperty("sender")
	private String sender;
	
	@JsonProperty("email")
	private String email;
	
	@JsonProperty("topic")
	private String topic;
	
	@JsonProperty("period")
	private String period;
	
	@JsonProperty("fromdate")
	private String fromDate;
	
	@JsonProperty("todate")
	private String toDate;

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTopic() {
		return StringUtils.isEmpty(topic) ? null : topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getFromDate() {
		return StringUtils.isEmpty(fromDate) ? null : fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return StringUtils.isEmpty(toDate) ? null : toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

}
