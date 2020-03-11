package net.fashiongo.webadmin.model.pojo.payment.parameter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.rkpunjal.sqlsafe.SQLInjectionSafe;

import io.swagger.annotations.ApiModelProperty;
import lombok.Setter;
import net.fashiongo.webadmin.controller.validator.SQLInjectionSafeWithKeywordsFilter;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.Pattern;

@Setter
public class GetAllSavedCreditCardInfoParameter {

    private static final String ALLOW_PATTERN = "^$|[a-zA-Z0-9\\s !&,-.?_\']+$";
    private static final String ALLOW_PATTERN_MESSAGE = "Special character not allowed";

    @ApiModelProperty(required = false, example = "1")
    @JsonProperty("pagenum")
    private Integer pageNum;

    @ApiModelProperty(required = false, example = "30")
    @JsonProperty("pagesize")
    private Integer pageSize;

    @ApiModelProperty(hidden = true)
    @JsonProperty("cardid")
    @SQLInjectionSafeWithKeywordsFilter
    @Pattern(regexp = ALLOW_PATTERN, message = ALLOW_PATTERN_MESSAGE)
    private String cardID;

    @ApiModelProperty(hidden = true)
    @JsonProperty("isdefaultcard")
    @SQLInjectionSafeWithKeywordsFilter
    @Pattern(regexp = ALLOW_PATTERN, message = ALLOW_PATTERN_MESSAGE)
    private String defaultCard;

    @ApiModelProperty(hidden = true)
    @JsonProperty("cardtypeid")
    private Integer cardTypeID;

    @ApiModelProperty(hidden = true)
    @JsonProperty("cardstatusid")
    private Integer cardStatusID;

    @ApiModelProperty(hidden = true)
    @JsonProperty("billingid")
    @SQLInjectionSafeWithKeywordsFilter
    @Pattern(regexp = ALLOW_PATTERN, message = ALLOW_PATTERN_MESSAGE)
    private String billingID;

    @ApiModelProperty(required = false, example = "ALL")
    @JsonProperty("creditcountry")
    @SQLInjectionSafeWithKeywordsFilter
    @Pattern(regexp = ALLOW_PATTERN, message = ALLOW_PATTERN_MESSAGE)
    private String creditCountry;

    @ApiModelProperty(required = false, example = "ALL")
    @JsonProperty("creditstate")
    @SQLInjectionSafeWithKeywordsFilter
    @Pattern(regexp = ALLOW_PATTERN, message = ALLOW_PATTERN_MESSAGE)
    private String creditState;

    @ApiModelProperty(hidden = true)
    @JsonProperty("buyer")
    @SQLInjectionSafe
    @Pattern(regexp = ALLOW_PATTERN, message = ALLOW_PATTERN_MESSAGE)
    private String buyer;

    @ApiModelProperty(hidden = true)
    @JsonProperty("referenceid")
    @SQLInjectionSafeWithKeywordsFilter
    @Pattern(regexp = ALLOW_PATTERN, message = ALLOW_PATTERN_MESSAGE)
    private String referenceID;

    @ApiModelProperty(required = false, example = "CreatedOn")
    @JsonProperty("orderby")
    @SQLInjectionSafeWithKeywordsFilter
    @Pattern(regexp = ALLOW_PATTERN, message = ALLOW_PATTERN_MESSAGE)
    private String orderBy;

    @ApiModelProperty(required = false, example = "Desc")
    @JsonProperty("orderbygubun")
    @SQLInjectionSafeWithKeywordsFilter
    @Pattern(regexp = ALLOW_PATTERN, message = ALLOW_PATTERN_MESSAGE)
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
        return StringUtils.isEmpty(defaultCard) ? null : defaultCard.equals("1");
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
