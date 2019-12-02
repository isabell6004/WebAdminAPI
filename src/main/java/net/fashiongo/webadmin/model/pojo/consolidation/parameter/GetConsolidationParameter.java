package net.fashiongo.webadmin.model.pojo.consolidation.parameter;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetConsolidationParameter {
	@JsonProperty("periodtype")
	@ApiModelProperty(required = false, example="6")
	private Integer periodType;

	@JsonProperty("pagenum")
	@ApiModelProperty(required = false, example="1")
	private Integer pageNum;

	@JsonProperty("pagesize")
	private Integer pageSize;

	@JsonProperty("dtfrom")
	private LocalDateTime dtFrom;

	@JsonProperty("dtto")
	private LocalDateTime dtTo;
	
	@JsonProperty("datecolumn")
	private String dateColumn;

	@JsonProperty("bshipped")
	private Integer bshipped;

	@JsonProperty("paymentSatus")
	private Integer paymentSatus;
	
	@JsonProperty("wn")
	private String wn;

	@JsonProperty("rn")
	private String rn;

	@JsonProperty("pn")
	private String pn;
	
	@JsonProperty("cn")
	private String cn;

	@JsonProperty("tn")
	private String tn;
	
	@JsonProperty("orderby")
	private String orderBy;
	
}
