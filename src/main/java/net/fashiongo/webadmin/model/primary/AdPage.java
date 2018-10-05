package net.fashiongo.webadmin.model.primary;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Nayeon Kim
 */
@Entity
@Table(name = "Ad_Page")
public class AdPage implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("PageID")
	@Column(name = "PageID")
	private Integer pageID;
	
	@JsonProperty("PageName")
	@Column(name = "PageName")
	private String pageName;

	@JsonProperty("PageUrl")
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
