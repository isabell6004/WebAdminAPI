package net.fashiongo.webadmin.model.pojo.parameter;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import net.fashiongo.webadmin.model.pojo.GroupData;

/**
 * 
 * @author Incheol Jung
 */
public class SetsecuritygroupParameter {
	
	@ApiModelProperty(required = true, example="1")
	private Integer gid;
	
	@ApiModelProperty(required = true, example="Marketing team")
	private String groupname;
	
	@ApiModelProperty(required = true, example="Marketing Team")
	private String description;
	
	@ApiModelProperty(required = true)
	private List<GroupData> gpdata;
	
	@ApiModelProperty(required = true, example="true")
	private boolean groupactive;
	
	@ApiModelProperty(required = true, example="true")
	private boolean applyall;

	public Integer getGid() {
		return gid;
	}

	public void setGid(Integer gid) {
		this.gid = gid;
	}

	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<GroupData> getGpdata() {
		return gpdata;
	}

	public void setGpdata(List<GroupData> gpdata) {
		this.gpdata = gpdata;
	}

	public boolean getGroupactive() {
		return groupactive;
	}

	public void setGroupactive(boolean groupactive) {
		this.groupactive = groupactive;
	}

	public boolean getApplyall() {
		return applyall;
	}

	public void setApplyall(boolean applyall) {
		this.applyall = applyall;
	}
	
}