package net.fashiongo.webadmin.model.pojo.response;

import java.io.Serializable;
import java.util.List;

import net.fashiongo.webadmin.model.pojo.ProductColor;

/**
 * 
 * @author Incheol Jung
 */
public class GetProductColorResponse implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private List<ProductColor> colors;
	public List<ProductColor> getColors() {
		return colors;
	}
	public void setColors(List<ProductColor> colors) {
		this.colors = colors;
	}
}
