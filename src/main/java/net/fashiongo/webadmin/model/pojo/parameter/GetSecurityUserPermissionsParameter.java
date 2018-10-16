package net.fashiongo.webadmin.model.pojo.parameter;

import org.apache.commons.lang3.StringUtils;

import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @author Incheol Jung
 */
public class GetSecurityUserPermissionsParameter {
	@ApiModelProperty(required = false, example="1")
	private String appid;
	
	@ApiModelProperty(required = false, example="0")
	private String userid;
	
	@ApiModelProperty(required = false, example="1")
	private String groupid;
	
	public Integer getAppid() {
		return StringUtils.isEmpty(appid) ? null : Integer.parseInt(appid);
	}
	public Integer getUserid() {
		return StringUtils.isEmpty(userid) ? null : Integer.parseInt(userid);
	}
	public Integer getGroupid() {
		return StringUtils.isEmpty(groupid) ? null : Integer.parseInt(groupid);
	}
}