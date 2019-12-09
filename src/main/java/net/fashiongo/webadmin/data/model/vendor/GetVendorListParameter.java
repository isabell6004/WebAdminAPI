package net.fashiongo.webadmin.data.model.vendor;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
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

    @JsonProperty(value = "FGExclusiveType")
    private Integer fgExclusiveType;

    //actualopenfrom:
    //actualopento:
    //avgorderamountfrom:
    //avgorderamountto:
    //checkoutfrom:
    //checkoutto:
    //adspentamountfrom:
    //adspentamountto:
    //adfrom:
    //adto:
    //recurringfrom:
    //recurringto:
}
