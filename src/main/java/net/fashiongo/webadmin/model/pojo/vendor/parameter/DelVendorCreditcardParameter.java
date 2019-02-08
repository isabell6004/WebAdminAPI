package net.fashiongo.webadmin.model.pojo.vendor.parameter;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * 
 * @author DAHYE
 *
 */
@Data
public class DelVendorCreditcardParameter implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("VendorCreditCardIDs")
	private String vendorCreditCardIDs;
	
}
