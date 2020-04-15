package net.fashiongo.webadmin.data.model.payment;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
    private String paymentDateFrom;

    @JsonProperty(value = "PaymentDateTo")
    private String paymentDateTo;
    
    @JsonProperty(value = "AppliedDateFrom")
    private String appliedDateFrom;

    @JsonProperty(value = "AppliedDateTo")
    private String appliedDateTo;
    
    @JsonProperty(value = "NetAmount")
    private BigDecimal netAmount;

    @JsonProperty(value = "NeedtoBill")
    private Integer needtoBill;

    @JsonProperty(value = "Orderby")
    private String orderBy;
 
    public LocalDateTime getPaymentDateFrom() {
        if (StringUtils.isNotEmpty(paymentDateFrom)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy HH:mm:ss");
            return LocalDateTime.parse(paymentDateFrom, formatter);
        }
        return null;
    }

    public LocalDateTime getPaymentDateTo() {
        if (StringUtils.isNotEmpty(paymentDateTo)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy HH:mm:ss");
            return LocalDateTime.parse(paymentDateTo, formatter);
        }
        return null;
    }    
    
    public LocalDateTime getAppliedDateFrom() {
        if (StringUtils.isNotEmpty(appliedDateFrom)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy HH:mm:ss");
            return LocalDateTime.parse(appliedDateFrom, formatter);
        }
        return null;
    }

    public LocalDateTime getAppliedDateTo() {
        if (StringUtils.isNotEmpty(appliedDateTo)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy HH:mm:ss");
            return LocalDateTime.parse(appliedDateTo, formatter);
        }
        return null;
    }      
}
