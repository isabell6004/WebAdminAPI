package net.fashiongo.webadmin.model.pojo.parameter;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author Dahye Jeong
 */

public class SetSecurityAccessIpParameter {
	@ApiModelProperty(required = false, example="18")
	private Integer ipid;
	@ApiModelProperty(required = false, example="103.243.200.12")
	private String ip;
	@ApiModelProperty(required = false, example="Seoul")
	private String description;
	@ApiModelProperty(required = false, example="")
	private String office;
	public Integer getIpid() {
		return ipid;
	}
	public void setIpid(Integer ipid) {
		this.ipid = ipid;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getOffice() {
		return office;
	}
	public void setOffice(String office) {
		this.office = office;
	}
	
}
