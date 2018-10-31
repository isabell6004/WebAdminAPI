package net.fashiongo.webadmin.model.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Nayeon Kim
 */
public class ProductSelectCheck {
	@JsonProperty("ItemSelectCheck")
	private Integer itemSelectCheck;

	public Integer getItemSelectCheck() {
		return itemSelectCheck;
	}

	public void setItemSelectCheck(Integer itemSelectCheck) {
		this.itemSelectCheck = itemSelectCheck;
	}
}
