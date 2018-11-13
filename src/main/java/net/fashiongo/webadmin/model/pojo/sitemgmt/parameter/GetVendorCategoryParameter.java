package net.fashiongo.webadmin.model.pojo.sitemgmt.parameter;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

public class GetVendorCategoryParameter implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String wholesalerid;

	public Integer getWholesalerid() {
		return StringUtils.isEmpty(this.wholesalerid) ? 0 : Integer.parseInt(this.wholesalerid);
	}

	public void setWholesalerid(String wholesalerid) {
		this.wholesalerid = wholesalerid;
	}
}