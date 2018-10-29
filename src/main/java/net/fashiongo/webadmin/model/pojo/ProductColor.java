package net.fashiongo.webadmin.model.pojo;

import java.io.Serializable;

/**
 * 
 * @author Incheol Jung
 */
public class ProductColor implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer colorID;
	private String color;
	private Boolean active;
	
	public Integer getColorID() {
		return colorID;
	}
	public void setColorID(Integer colorID) {
		this.colorID = colorID;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	
}
