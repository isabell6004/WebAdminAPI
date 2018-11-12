package net.fashiongo.webadmin.model.pojo.common.parameter;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
/**
 * 
 * @author DAHYE
 *
 */
@SuppressWarnings("serial")
public class GetServerHeartBeatParameter implements Serializable {
	@ApiModelProperty(required = false, example="1539669765324")
	private Long q;

	public Long getQ() {
		return q;
	}

	public void setQ(Long q) {
		this.q = q;
	}
	
}
