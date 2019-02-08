package net.fashiongo.webadmin.model.pojo.parameter;

import java.io.Serializable;

import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author Reo
 *
 */
public class DelVendorBlockParameter implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("BlockId")
	private Integer blockID;
	
	@JsonProperty("WholeSalerID")
	private Integer wholeSalerID;

	public Integer getBlockID() {
		return StringUtils.isEmpty(blockID) ? 0 : blockID;
	}

	public void setBlockID(Integer blockID) {
		this.blockID = blockID;
	}

	public Integer getWholeSalerID() {
		return wholeSalerID;
	}

	public void setWholeSalerID(Integer wholeSalerID) {
		this.wholeSalerID = wholeSalerID;
	}
	
	
}
