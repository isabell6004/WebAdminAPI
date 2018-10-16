package net.fashiongo.webadmin.model.pojo.parameter;

import io.swagger.annotations.ApiModelProperty;

public class GetServerHeartBeatParameter {
	@ApiModelProperty(required = false, example="1539669765324")
	private Long q;

	public Long getQ() {
		return q;
	}

	public void setQ(Long q) {
		this.q = q;
	}
	
}
