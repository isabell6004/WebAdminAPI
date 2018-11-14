package net.fashiongo.webadmin.model.pojo.parameter;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

import io.swagger.annotations.ApiModelProperty;

public class GetProductListParameter implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(required = true, example = "2858")
	private String wholesalerid;
	@ApiModelProperty(required = true, example = "12")
	private String vendorcategoryid;
	@ApiModelProperty(required = true, example = "test")
	private String productname;
	
	public Integer getWholesalerid() {
		return StringUtils.isEmpty(this.wholesalerid) ? 0 : Integer.parseInt(this.wholesalerid);
	}
	public void setWholesalerid(String wholesalerid) {
		this.wholesalerid = wholesalerid;
	}
	public Integer getVendorcategoryid() {
		return StringUtils.isEmpty(this.vendorcategoryid) ? 0 : Integer.parseInt(this.vendorcategoryid);
	}
	public void setVendorcategoryid(String vendorcategoryid) {
		this.vendorcategoryid = vendorcategoryid;
	}
	public String getProductname() {
		return StringUtils.isEmpty(this.productname) ? "" : this.productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	
}