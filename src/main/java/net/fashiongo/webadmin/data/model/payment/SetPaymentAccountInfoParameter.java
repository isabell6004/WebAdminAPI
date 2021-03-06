package net.fashiongo.webadmin.data.model.payment;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SetPaymentAccountInfoParameter {
    @JsonProperty(value = "businessName")
    private String businessName;

    @JsonProperty(value = "email")
    private String email;

    @JsonProperty(value = "street")
    private String street;

    @JsonProperty(value = "city")
    private String city;

    @JsonProperty(value = "state")
    private String state;

    @JsonProperty(value = "zipCode")
    private String zipCode;

    @JsonProperty(value = "country")
    private String country;
    
    @JsonProperty(value = "phoneNumber")
    private String phoneNumber;

    @JsonProperty(value = "maxPayoutPerDay")
    private Integer maxPayoutPerDay;

    @JsonProperty(value = "payoutSchedule")
    private Integer payoutSchedule;

    @JsonProperty(value = "weekday")
    private Integer weekday;

    @JsonProperty(value = "dayOfMonth")
    private Integer dayOfMonth;

    @JsonProperty(value = "statementDescriptor")
    private String statementDescriptor;

    @JsonProperty(value = "modifiedBy")
    private String modifiedBy;

    @JsonProperty(value = "referenceId")
    private String referenceId;

    @JsonProperty(value = "wholeSalerId")
    private Integer wholeSalerId;
    
    @JsonProperty(value = "isLocked")
    private Boolean isLocked;
    
    @JsonProperty(value="entityType")
    private String entityType;
    
    @JsonProperty(value = "owners")
    private List<VendorPayoutInfoOwner> owners;
    
    @JsonProperty(value="deleteOwnerIds")
    private List<Integer> deleteOwnerIds;

    @JsonProperty(value = "isPayoutScheduleLock")
    private Boolean isPayoutScheduleLock;

    @JsonProperty(value = "isPayoutScheduleLockChanged")
    private Boolean isPayoutScheduleLockChanged;
}
