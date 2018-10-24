package net.fashiongo.webadmin.model.pojo.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.fashiongo.webadmin.model.pojo.SecurityLogs;
import net.fashiongo.webadmin.model.pojo.SecurityLogsColumn;

/**
 * @author Nayeon Kim
 */
public class GetSecurityLogsResponse {
	@JsonProperty("Table")
	private List<SecurityLogs> securityLogs;

	@JsonProperty("Table1")
	private List<SecurityLogsColumn> securityLogsColumn;

	public List<SecurityLogs> getSecurityLogs() {
		return securityLogs;
	}

	public void setSecurityLogs(List<SecurityLogs> securityLogs) {
		this.securityLogs = securityLogs;
	}

	public List<SecurityLogsColumn> getSecurityLogsColumn() {
		return securityLogsColumn;
	}

	public void setSecurityLogsColumn(List<SecurityLogsColumn> securityLogsColumn) {
		this.securityLogsColumn = securityLogsColumn;
	}
}
