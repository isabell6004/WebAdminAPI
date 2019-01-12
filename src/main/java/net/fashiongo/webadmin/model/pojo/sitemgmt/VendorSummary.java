package net.fashiongo.webadmin.model.pojo.sitemgmt;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Incheol Jung
 */
public class VendorSummary {
	@JsonProperty("WholeSalerID")
	private Integer wholeSalerID;
	
	@JsonProperty("CompanyName")
    private String companyName;
	
	@JsonProperty("DirName")
    private String dirName;
	
	@JsonProperty("ItemCount")
    private Integer itemCount;
	
	@JsonProperty("AccessStatus")
    private Integer accessStatus;
    
	public Integer getWholeSalerID() {
		return wholeSalerID;
	}
	public void setWholeSalerID(Integer wholeSalerID) {
		this.wholeSalerID = wholeSalerID;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getDirName() {
		return dirName;
	}
	public void setDirName(String dirName) {
		this.dirName = dirName;
	}
	public Integer getItemCount() {
		return itemCount;
	}
	public void setItemCount(Integer itemCount) {
		this.itemCount = itemCount;
	}
	public Integer getAccessStatus() {
		return accessStatus;
	}
	public void setAccessStatus(Integer accessStatus) {
		this.accessStatus = accessStatus;
	}
    
}