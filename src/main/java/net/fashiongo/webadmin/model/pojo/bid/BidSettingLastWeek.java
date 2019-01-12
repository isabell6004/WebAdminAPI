package net.fashiongo.webadmin.model.pojo.bid;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BidSettingLastWeek {
	@JsonProperty("dt")
	@Column(name = "dt")
	private String dt;
	
	@JsonProperty("dv")
	@Column(name = "dv")
	private String dv;

	public String getDt() {
		return dt;
	}

	public void setDt(String dt) {
		this.dt = dt;
	}

	public String getDv() {
		return dv;
	}

	public void setDv(String dv) {
		this.dv = dv;
	}
	
}
