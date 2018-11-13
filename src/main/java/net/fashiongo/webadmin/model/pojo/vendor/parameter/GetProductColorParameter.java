package net.fashiongo.webadmin.model.pojo.vendor.parameter;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @author Incheol Jung
 */
public class GetProductColorParameter implements Serializable{
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(required = false, example="123456")
	private Integer productid;

	public Integer getProductid() {
		return productid;
	}

	public void setProductid(Integer productid) {
		this.productid = productid;
	}
	
}