package net.fashiongo.webadmin.model.pojo;

import javax.persistence.Column;

/**
 * @author Nayeon Kim
 */
public class AdSettingList {
	@Column(name = "PageID")
	private Integer pageID;
	@Column(name = "PageName")
	private String pageName;
	@Column(name = "PageUrl")
	private String pageUrl;
	
	public Integer getPageID() {
		return pageID;
	}
	public void setPageID(Integer pageID) {
		this.pageID = pageID;
	}
	public String getPageName() {
		return pageName;
	}
	public void setPageName(String pageName) {
		this.pageName = pageName;
	}
	public String getPageUrl() {
		return pageUrl;
	}
	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}
}
