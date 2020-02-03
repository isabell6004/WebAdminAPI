package net.fashiongo.webadmin.data.model.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
public class OrderPayment {
    @JsonProperty("OrderPaymentStatusID")
    private Integer orderPaymentStatusID;

    @JsonProperty("ReferenceID")
    private Integer referenceID;

    @JsonProperty("IsOrder")
    private Integer isOrder;

    @JsonProperty("PaymentStatusID")
    private Integer paymentStatusID;

    @JsonProperty("PrePaymentStatusID")
    private Integer prePaymentStatusID;

    @JsonProperty("RetailerID")
    private Integer retailerID;

    @JsonProperty("CreatedOn")
    private LocalDateTime createdOn;

    @JsonProperty("CreatedBy")
    private String createdBy;

    @JsonProperty("ModifiedOn")
    private LocalDateTime modifiedOn;

    @JsonProperty("ModifiedBy")
    private String modifiedBy;

    @JsonProperty("PONumber")
    private String poNumber;

    @JsonProperty("CompanyName")
    private String companyName;

    @JsonProperty("FirstName")
    private String firstName;

    @JsonProperty("LastName")
    private String lastName;

    @JsonProperty("TotAmt")
    private BigDecimal totAmt;

    @JsonProperty("WholeSalerID")
    private Integer wholeSalerID;

    @JsonProperty("WholeCompanyName")
    private String wholeCompanyName;

    @JsonProperty("OrderID")
    private Integer orderID;

    @JsonProperty("OrderSessionGUID")
    private String orderSessionGUID;

    @JsonProperty("OrderStatus")
    private String orderStatus;

    @JsonProperty("isConsolidation")
    private Boolean isConsolidation;

    public OrderPayment() {
    }

    public OrderPayment(Integer orderPaymentStatusID, Integer referenceID, Integer isOrder, Integer paymentStatusID, Integer prePaymentStatusID, Integer retailerID, Timestamp createdOn, String createdBy, Timestamp modifiedOn, String modifiedBy, String poNumber, String companyName, String firstName, String lastName, BigDecimal totAmt, Integer wholeSalerID, String wholeCompanyName, Integer orderID, String orderSessionGUID, Boolean isConsolidation, String orderStatus) {
        this.orderPaymentStatusID = orderPaymentStatusID;
        this.referenceID = referenceID;
        this.isOrder = isOrder;
        this.paymentStatusID = paymentStatusID;
        this.prePaymentStatusID = prePaymentStatusID;
        this.retailerID = retailerID;
        this.createdOn = createdOn == null ? null : createdOn.toLocalDateTime();
        this.createdBy = createdBy;
        this.modifiedOn = modifiedOn == null ? null : modifiedOn.toLocalDateTime();
        this.modifiedBy = modifiedBy;
        this.poNumber = poNumber;
        this.companyName = companyName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.totAmt = totAmt;
        this.wholeSalerID = wholeSalerID;
        this.wholeCompanyName = wholeCompanyName;
        this.orderID = orderID;
        this.orderSessionGUID = orderSessionGUID;
        this.isConsolidation = isConsolidation;
        this.orderStatus = orderStatus;
    }
}
