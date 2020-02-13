package net.fashiongo.webadmin.model.pojo.sitemgmt.parameter;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetSEOParameter {

	@ApiModelProperty(required = false, example = "1")
	public Integer pagenum;

	@ApiModelProperty(required = false, example = "50")
	public Integer pagesize;

}
