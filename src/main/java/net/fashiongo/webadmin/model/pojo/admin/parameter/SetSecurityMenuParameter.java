package net.fashiongo.webadmin.model.pojo.admin.parameter;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import net.fashiongo.webadmin.model.pojo.admin.GroupData;

/**
 * 
 * @author Jiwon Kim
 */
public class SetSecurityMenuParameter {
	
	@ApiModelProperty(required = true, example="0")
	private Integer menuid;
	
	@ApiModelProperty(required = true, example="1")
	private Integer parentid;
	
	@ApiModelProperty(required = true, example="2")
	private Integer resourceid;

	@ApiModelProperty(required = true, example="3")
	private Integer applicationid;
	
	@ApiModelProperty(required = true, example="test")
	private String menuname;
	
	@ApiModelProperty(required = true, example="Marketing Team")
	private String routepath;
	
	@ApiModelProperty(required = true, example="Marketing Team")
	private String menuicon;
	
	@ApiModelProperty(required = true, example="0")
	private Integer listorder;
	
	@ApiModelProperty(required = true, example="1")
	private Boolean visible;
	
	@ApiModelProperty(required = true, example="1")
	private Boolean active;

	public Integer getMenuid() {
		return menuid;
	}

	public void setMenuid(Integer menuid) {
		this.menuid = menuid;
	}

	public Integer getParentid() {
		return parentid;
	}

	public void setParentid(Integer parentid) {
		this.parentid = parentid;
	}

	public Integer getResourceid() {
		return resourceid;
	}

	public void setResourceid(Integer resourceid) {
		this.resourceid = resourceid;
	}

	public Integer getApplicationid() {
		return applicationid;
	}

	public void setApplicationid(Integer applicationid) {
		this.applicationid = applicationid;
	}

	public String getMenuname() {
		return menuname;
	}

	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}

	public String getRoutepath() {
		return routepath;
	}

	public void setRoutepath(String routepath) {
		this.routepath = routepath;
	}

	public String getMenuicon() {
		return menuicon;
	}

	public void setMenuicon(String menuicon) {
		this.menuicon = menuicon;
	}

	public Integer getListorder() {
		return listorder;
	}

	public void setListorder(Integer listorder) {
		this.listorder = listorder;
	}

	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
	
	
	
}