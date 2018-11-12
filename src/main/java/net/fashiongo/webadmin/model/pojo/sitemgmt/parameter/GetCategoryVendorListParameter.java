package net.fashiongo.webadmin.model.pojo.sitemgmt.parameter;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Nayeon Kim
 */
public class GetCategoryVendorListParameter {
	private String categoryid;
	private String vendorname;
	
	public Integer getCategoryid() {
		return StringUtils.isEmpty(categoryid) ? 0 : Integer.parseInt(categoryid);
	}
	public void setCategoryid(String categoryid) {
		this.categoryid = categoryid;
	}
	public String getVendorname() {
		return StringUtils.isEmpty(vendorname) ? null : vendorname;
	}
	public void setVendorname(String vendorname) {
		this.vendorname = vendorname;
	}
}
