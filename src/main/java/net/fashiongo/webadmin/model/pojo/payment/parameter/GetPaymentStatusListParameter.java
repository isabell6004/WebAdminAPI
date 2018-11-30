package net.fashiongo.webadmin.model.pojo.payment.parameter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Setter;

/**
 * 
 * @author DAHYE
 *
 */
@Setter
public class GetPaymentStatusListParameter {
	
	@ApiModelProperty(required = false, example="1")
	@JsonProperty("PageNum")
	private String pageNum;
	
	@ApiModelProperty(required = false, example="20")
	@JsonProperty("PageSize")
	private String pageSize;
	
	@ApiModelProperty(required = false, example="2858")
	@JsonProperty("WholeSalerID")
	private String wholeSalerID;
	
	@ApiModelProperty(required = false, example="2")
	@JsonProperty("PaymentStatusID")
	private String paymentStatusID;
	
	@ApiModelProperty(required = false, example="2017-01-01 00:00:00.000")
	@JsonProperty("FromDate")
	private String fromDate;
	
	@ApiModelProperty(required = false, example="2017-12-31 00:00:00.000")
	@JsonProperty("ToDate")
	private String toDate;
	
	@ApiModelProperty(required = false, example="test")
	@JsonProperty("PONumber")
	private String poNumber;
	
	@ApiModelProperty(required = false, example="2")
	@JsonProperty("ConsolidationID")
	private String consolidationID;
	
	@ApiModelProperty(required = false, example="test")
	@JsonProperty("BuyerName")
	private String buyerName;
	
	@ApiModelProperty(required = false, example="0")
	@JsonProperty("TransactionType")
	private String transactionType;
	
	@ApiModelProperty(required = false, example="1")
	@JsonProperty("searchSuccess")
	private String searchSuccess;
	
	@ApiModelProperty(required = false, example="1")
	@JsonProperty("OrderBy")
	private String orderBy;

	public Integer getPageNum() {
		return StringUtils.isEmpty(pageNum) ? 1 : Integer.parseInt(pageNum);
	}

	public Integer getPageSize() {
		return StringUtils.isEmpty(pageSize) ? 10 : Integer.parseInt(pageSize);
	}

	public Integer getWholeSalerID() {
		return StringUtils.isEmpty(wholeSalerID) ? null : Integer.parseInt(wholeSalerID);
	}

	public Integer getPaymentStatusID() {
		return StringUtils.isEmpty(paymentStatusID) ? null : Integer.parseInt(paymentStatusID);
	}

	public LocalDateTime getFromDate() {
		if (StringUtils.isNotEmpty(fromDate)) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy HH:mm:ss");
			return LocalDateTime.parse(fromDate, formatter);
		}
		return null;
	}
	
	

	public LocalDateTime getToDate() {
		if (StringUtils.isNotEmpty(toDate)) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy HH:mm:ss");
			return LocalDateTime.parse(toDate, formatter);
		}
		return null;
	}

	public String getPoNumber() {
		return StringUtils.isEmpty(poNumber) ? null : poNumber;
	}

	public Integer getConsolidationID() {
		return StringUtils.isEmpty(consolidationID) ? null : Integer.parseInt(consolidationID);
	}

	public String getBuyerName() {
		return StringUtils.isEmpty(buyerName) ? null : buyerName;
	}

	public Integer getTransactionType() {
		return StringUtils.isEmpty(transactionType) ? null : Integer.parseInt(transactionType);
	}

	public Integer getSearchSuccess() {
		return StringUtils.isEmpty(searchSuccess) ? null : Integer.parseInt(searchSuccess);
	}

	public String getOrderBy() {
		return StringUtils.isEmpty(orderBy) ? null : orderBy;
	}


	
}
