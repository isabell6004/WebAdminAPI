package net.fashiongo.webadmin.data.model.buyer;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@AllArgsConstructor
@Getter
@Setter
public class BuyerSearch {

    public BuyerSearch(Integer retailerID, String companyName, String firstName, String lastName, String userID,String active, Integer currentStatus,Timestamp startingDate) {
        this.retailerID = retailerID;
        this.companyName = companyName;
        this.firstName=firstName;
        this.lastName =lastName;
        this.userID = userID;
        this.active= active;
        this.currentStatus = currentStatus;
        this.startingDate = Optional.ofNullable(startingDate).map(Timestamp::toLocalDateTime).orElse(null);
    }

    @Column(name = "RetailerID")
    @JsonProperty("RetailerID")
    private Integer retailerID;

    @Column(name = "CompanyName")
    @JsonProperty("CompanyName")
    private String companyName;



    @Column(name = "FirstName")
    @JsonProperty("FirstName")
    private String firstName;


    @Column(name = "LastName")
    @JsonProperty("LastName")
    private String lastName;

    @Column(name = "UserID")
    @JsonProperty("UserID")
    private String userID;



    @Column(name = "Active")
    @JsonProperty("Active")
    private String active;


    @Column(name = "CurrentStatus")
    @JsonProperty("CurrentStatus")
    private Integer currentStatus;


    @Column(name = "StartingDate")
    @JsonProperty("StartingDate")
    private LocalDateTime startingDate;

    @Column(name = "UserID")
    @JsonProperty("UserID")
    private String userID;

    @Column(name = "rowno")
    @JsonProperty("rowno")
    private Integer rowno;


}
