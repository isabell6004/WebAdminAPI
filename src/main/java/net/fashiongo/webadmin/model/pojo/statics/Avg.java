package net.fashiongo.webadmin.model.pojo.statics;

import javax.persistence.Column;

/**
 * 
 * @author Incheol Jung
 */
public class Avg {
	@Column(name = "VendorAvgDuration")
	private Integer avg;

	public Integer getAvg() {
		return avg;
	}

	public void setAvg(Integer avg) {
		this.avg = avg;
	}
}
