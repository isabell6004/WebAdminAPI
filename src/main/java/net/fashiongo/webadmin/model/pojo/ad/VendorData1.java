package net.fashiongo.webadmin.model.pojo.ad;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * 
 * @author Jiwon Kim
 */
public class VendorData1 {


	@JsonProperty("WholeSalerID")
	@Column(name = "WholeSalerID")
	private Integer wholeSalerID;
	@JsonProperty("FICount")
	@Column(name = "FICount")
	private Integer fICount;
	public Integer getWholeSalerID() {
		return wholeSalerID;
	}
	public void setWholeSalerID(Integer wholeSalerID) {
		this.wholeSalerID = wholeSalerID;
	}
	public Integer getfICount() {
		return fICount;
	}
	public void setfICount(Integer fICount) {
		this.fICount = fICount;
	}
	
}