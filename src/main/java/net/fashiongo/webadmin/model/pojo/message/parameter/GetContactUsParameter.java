package net.fashiongo.webadmin.model.pojo.message.parameter;

import java.time.LocalDateTime;
import java.util.Date;

import com.github.rkpunjal.sqlsafe.SQLInjectionSafe;
import net.fashiongo.webadmin.controller.validator.SQLInjectionSafeWithKeywordsFilter;
import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Pattern;

/**
 * 
 * @author Reo
 *
 */
public class GetContactUsParameter {

	private static final String ALLOW_PATTERN = "^$|[a-zA-Z0-9\\s /:!&,-.?_\']+$";
	private static final String ALLOW_PATTERN_MESSAGE = "Special character not allowed";

	@JsonProperty("pagesize")
	private Integer pageSize;
	
	@JsonProperty("pagenum")
	private Integer pageNum;
	
	@JsonProperty("sender")
	@SQLInjectionSafe
	@Pattern(regexp = ALLOW_PATTERN, message = ALLOW_PATTERN_MESSAGE)
	private String sender;
	
	@JsonProperty("email")
	@SQLInjectionSafe
	private String email;
	
	@JsonProperty("topic")
	@SQLInjectionSafeWithKeywordsFilter
	@Pattern(regexp = ALLOW_PATTERN, message = ALLOW_PATTERN_MESSAGE)
	private String topic;
	
	@JsonProperty("period")
	@SQLInjectionSafeWithKeywordsFilter
	@Pattern(regexp = ALLOW_PATTERN, message = ALLOW_PATTERN_MESSAGE)
	private String period;
	
	@JsonProperty("fromdate")
	@SQLInjectionSafeWithKeywordsFilter
	@Pattern(regexp = ALLOW_PATTERN, message = ALLOW_PATTERN_MESSAGE)
	private String fromDate;
	
	@JsonProperty("todate")
	@SQLInjectionSafeWithKeywordsFilter
	@Pattern(regexp = ALLOW_PATTERN, message = ALLOW_PATTERN_MESSAGE)
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
