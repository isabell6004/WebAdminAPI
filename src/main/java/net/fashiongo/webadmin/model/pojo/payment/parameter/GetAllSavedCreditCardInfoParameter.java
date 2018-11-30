package net.fashiongo.webadmin.model.pojo.payment.parameter;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
import lombok.Setter;

/**
 * 
 * @author DAHYE
 *
 */
@Setter
public class GetAllSavedCreditCardInfoParameter {

	@ApiModelProperty(required = false, example="1")
	@JsonProperty("pagenum")
	private Integer pageNum;

	@ApiModelProperty(required = false, example="30")
	@JsonProperty("pagesize")
	private Integer pageSize;

	@ApiModelProperty(hidden=true)
	@JsonProperty("cardid")
	private String cardID;

	@ApiModelProperty(hidden=true)
	@JsonProperty("isdefaultcard")
	private Boolean defaultCard;

	@ApiModelProperty(hidden=true)
	@JsonProperty("cardtypeid")
	private Integer cardTypeID;

	@ApiModelProperty(hidden=true)
	@JsonProperty("cardstatusid")
	private Integer cardStatusID;

	@ApiModelProperty(hidden=true)
	@JsonProperty("billingid")
	private String billingID;

	@ApiModelProperty(required = false, example="ALL")
	@JsonProperty("creditcountry")
	private String creditCountry;

	@ApiModelProperty(required = false, example="ALL")
	@JsonProperty("creditstate")
	private String creditState;

	@ApiModelProperty(hidden=true)
	@JsonProperty("buyer")
	private String buyer;

	@ApiModelProperty(hidden=true)
	@JsonProperty("referenceid")
	private String referenceID;

	@ApiModelProperty(required = false, example="CreatedOn")
	@JsonProperty("orderby")
	private String orderBy;

	@ApiModelProperty(required = false, example="Desc")
	@JsonProperty("orderbygubun")
	private String orderGubn;

	public Integer getPageNum() {
		return pageNum == null ? 1 : pageNum;
	}

	public Integer getPageSize() {
		return pageSize == null ? 10 : pageSize;
	}

	public String getCardID() {
		return StringUtils.isEmpty(cardID) ? null : cardID;
	}

	public Boolean getDefaultCard() {
		return defaultCard;
	}

	public Integer getCardTypeID() {
		return cardTypeID == null ? 0 : cardTypeID;
	}

	public Integer getCardStatusID() {
		return cardStatusID == null ? 0 : cardStatusID;
	}

	public String getBillingID() {
		return StringUtils.isEmpty(billingID) ? null : billingID;
	}

	public String getCreditCountry() {
		return StringUtils.isEmpty(creditCountry) ? "" : creditCountry;
	}

	public String getCreditState() {
		return StringUtils.isEmpty(creditState) ? null : creditState;
	}

	public String getBuyer() {
		return StringUtils.isEmpty(buyer) ? null : buyer;
	}

	public String getReferenceID() {
		return StringUtils.isEmpty(referenceID) ? null : referenceID;
	}

	public String getOrderBy() {
		return StringUtils.isEmpty(orderBy) ? null : orderBy;
	}

	public String getOrderGubn() {
		return StringUtils.isEmpty(orderGubn) ? null : orderGubn;
	}	
	
	
}
