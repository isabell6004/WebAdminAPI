package net.fashiongo.webadmin.model.pojo.vendor.response;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import net.fashiongo.webadmin.model.pojo.vendor.VendorCreditCard;

/**
 * 
 * @author DAHYE
 *
 */
@Data
public class GetVendorCreditCardListResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("Table")
	private List<VendorCreditCard> vendorCreditCardList;
	
}
