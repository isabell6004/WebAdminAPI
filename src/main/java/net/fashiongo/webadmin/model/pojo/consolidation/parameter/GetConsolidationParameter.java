package net.fashiongo.webadmin.model.pojo.consolidation.parameter;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetConsolidationParameter {
	@JsonProperty("periodtype")
	@ApiModelProperty(required = false, example="1")
	private Integer periodType;

	@JsonProperty("pagenum")
	@ApiModelProperty(required = false, example="1")
	private Integer pageNum;

	@JsonProperty("pagesize")
	private Integer pageSize;

	@JsonProperty("dtfrom")
	@JsonFormat(pattern = "MM/dd/yyyy")
	private LocalDate dtFrom;

	@JsonProperty("dtto")
	@JsonFormat(pattern = "MM/dd/yyyy")
	private LocalDate dtTo;
	
	@JsonProperty("datecolumn")
	private String dateColumn;

	@JsonProperty("bshipped")
	private Integer bshipped;

	@JsonProperty("paymentStatus")
	private Integer paymentStatus;
	
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
