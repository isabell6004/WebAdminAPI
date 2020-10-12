package net.fashiongo.webadmin.data.model.vendor;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder(toBuilder = true)
public class GetVendorListParameter {
    @JsonProperty(value = "pagenum")
    private Integer pageNum;

    @JsonProperty(value = "pagesize")
    private Integer pageSize;

    @JsonProperty(value = "companyname")
    private String companyName;

    @JsonProperty(value = "companynamepartialmatch")
    private Boolean companyNamePartialMatch;

    @JsonProperty(value = "wholesalerid")
    private Integer wholeSalerID;

    @JsonProperty(value = "userid")
    private String userID;

    @JsonProperty(value = "useridpartialmatch")
    private Boolean userIdPartialMatch;

    @JsonProperty(value = "oldcompanyname")
    private String oldCompanyName;

    @JsonProperty(value = "CompanyType")
    private String companyType;

    @JsonProperty(value = "CategoryModel")
    private Integer categoryModel;

    @JsonProperty(value = "Status")
    private Integer status;

    @JsonProperty(value = "orderby")
    private String orderBy;

    @JsonProperty(value = "assigneduser")
    private Integer assignedUser;

    @JsonProperty(value = "country")
    private String country;

    @JsonProperty(value = "state")
    private String state;

    @JsonProperty(value = "location")
    private String location;

    @JsonProperty(value = "TypeOfContract")
    private String typeOfContract;

    @JsonProperty(value = "PhotoPlan")
    private String photoPlan;

    @JsonProperty(value = "ChooseType")
    private String chooseType;

    @JsonProperty(value = "Commission")
    private String commission;
    
    @JsonProperty(value = "SourceType")
    private Integer sourcetype;

    @JsonProperty(value = "FGExclusiveType")
    private Integer fgExclusiveType;

    @JsonProperty(value = "actualopenfrom")
    private String actualopenfrom;

    @JsonProperty(value = "actualopento")
    private String actualopento;

    @JsonProperty(value = "avgorderamountfrom")
    private BigDecimal avgorderamountfrom;

    @JsonProperty(value = "avgorderamountto")
    private BigDecimal avgorderamountto;

    @JsonProperty(value = "checkoutfrom")
    private String checkoutfrom;

    @JsonProperty(value = "checkoutto")
    private String checkoutto;

    @JsonProperty(value = "adspentamountfrom")
    private BigDecimal adspentamountfrom;

    @JsonProperty(value = "adspentamountto")
    private BigDecimal adspentamountto;

    @JsonProperty(value = "adfrom")
    private String adfrom;

    @JsonProperty(value = "adto")
    private String adto;

    @JsonProperty(value = "recurringfrom")
    private Integer recurringfrom;

    @JsonProperty(value = "recurringto")
    private Integer recurringto;

    @JsonProperty(value = "contractexpiredatefrom")
    private String contractexpiredatefrom;

    @JsonProperty(value = "contractexpiredateto")
    private String contractexpiredateto;
}
