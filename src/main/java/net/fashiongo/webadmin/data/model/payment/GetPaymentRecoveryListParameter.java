package net.fashiongo.webadmin.data.model.payment;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

@Getter
public class GetPaymentRecoveryListParameter {
    @JsonProperty(value = "Pagenum")
    private Integer pageNum;

    @JsonProperty(value = "Pagesize")
    private Integer pageSize;

    @JsonProperty(value = "ReferenceID")
    private Integer referenceID;

    @JsonProperty(value = "Referencetype")
    private Integer referencetype;

    @JsonProperty(value = "PaymentDateFrom")
    private Timestamp paymentDateFrom;

    @JsonProperty(value = "PaymentDateTo")
    private Timestamp paymentDateTo;
    
    @JsonProperty(value = "AppliedDateFrom")
    private Timestamp appliedDateFrom;

    @JsonProperty(value = "AppliedDateTo")
    private Timestamp appliedDateTo;
    
    @JsonProperty(value = "NetAmount")
    private BigDecimal netAmount;

    @JsonProperty(value = "NeedtoBill")
    private Boolean needtoBill;

    @JsonProperty(value = "Orderby")
    private String orderBy;
 
}
