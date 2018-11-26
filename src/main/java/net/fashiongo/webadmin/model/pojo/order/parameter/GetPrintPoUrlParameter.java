package net.fashiongo.webadmin.model.pojo.order.parameter;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author Reo
 *
 */
public class GetPrintPoUrlParameter {
	@JsonProperty("ordersessionguid")
	private String orderSessionGUID;
	
	@JsonProperty("oids")
	private String oids;
	
	@JsonProperty("t")
	private String t;
	
	@JsonProperty("resulttype")
	private String resultType;
	
	@JsonProperty("forpdf")
	private String forPdf;
	
	@JsonProperty("withimage")
	private String withImage;
	
	@JsonProperty("withvendorstyleno")
	private String withVendorStyleNo;

	public String getOrderSessionGUID() {
		return orderSessionGUID;
	}

	public void setOrderSessionGUID(String orderSessionGUID) {
		this.orderSessionGUID = orderSessionGUID;
	}

	public String getOids() {
		return oids.replace("[", "").replace("]", "");
	}

	public void setOids(String oids) {
		this.oids = oids;
	}

	public String getT() {
		return t;
	}

	public void setT(String t) {
		this.t = t;
	}

	public String getResultType() {
		return resultType;
	}

	public void setResultType(String resultType) {
		this.resultType = resultType;
	}

	public String getForPdf() {
		return forPdf;
	}

	public void setForPdf(String forPdf) {
		this.forPdf = forPdf;
	}

	public String getWithImage() {
		return withImage;
	}

	public void setWithImage(String withImage) {
		this.withImage = withImage;
	}

	public String getWithVendorStyleNo() {
		return withVendorStyleNo;
	}

	public void setWithVendorStyleNo(String withVendorStyleNo) {
		this.withVendorStyleNo = withVendorStyleNo;
	}
	
	
}
