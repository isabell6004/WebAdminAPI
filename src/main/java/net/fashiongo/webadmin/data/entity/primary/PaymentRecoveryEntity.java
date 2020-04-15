package net.fashiongo.webadmin.data.entity.primary;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "PaymentTransaction_Failure_Applied")
@Getter
@Setter
public class PaymentRecoveryEntity implements Serializable{

	private static final long serialVersionUID = 7285824159522183029L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TransactionFailureID")
    private Integer transactionFailureID;
    
    @Column(name = "TransactionType")
    private Integer transactionType;
    
    @Column(name = "ReferenceID")
    private Integer referenceID;
    
    @Column(name = "ReferenceTypeID")
    private Integer referenceTypeID;
    
    @Column(name = "PGReferenceID")
    private String pGReferenceID;
    
    @Column(name = "PaymentPGReferenceID")
    private String paymentPGReferenceID;
    
    @Column(name = "CreditCardID")
    private Integer creditCardID;
    
    @Column(name = "CreditCardReferenceID")
    private String creditCardReferenceID;
    
    @Column(name = "NetAmount")
    private BigDecimal netAmount;
    
    @Column(name = "TransferAmount")
    private BigDecimal transferAmount; 

    @Column(name = "PaymentDate")
    private Timestamp paymentDate;
   
    @Column(name = "AppliedDate")
    private Timestamp appliedDate;
    
    @Column(name = "BillingDate")
    private Timestamp billingDate;
    
    @Column(name = "NeedtoBill")
    private Boolean needtoBill;
    
    @Column(name = "CreatedOn")
    private Timestamp createdOn;
    
    @Column(name = "CreatedBy")
    private String createdBy;   
    
    @Column(name = "ModifiedOn")
    private Timestamp modifiedOn;
    
    @Column(name = "ModifiedBy")
    private String modifiedBy;   
   
}
