/**
 * 
 */
package net.fashiongo.webadmin.data.entity.primary;

import lombok.Getter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 
 * @author Incheol Jung
 */
@Entity
@Table(name = "TodayDeal")
@Getter
public class TodayDealEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "TodayDealID")
	private Integer todayDealId;
	
	@Column(name = "ProductID")
	private Integer productId;
	
	@Column(name = "Title")
	private String title;
	
	@Column(name = "Description")
	private String description;
	
	@Column(name = "TodayDealPrice")
	private BigDecimal todayDealPrice;
	
	@Column(name = "ToDate")
	private Timestamp toDate;
	
	@Column(name = "FromDate")
	private Timestamp fromDate;
	
	@Column(name = "CreatedOn")
	private Timestamp createdOn;
	
	@Column(name = "CreatedBy")
	private String createdBy;

	@Column(name = "ModifiedOn")
	private Timestamp modifiedOn;
	
	@Column(name = "AppliedOn")
	private Timestamp appliedOn;
	
	@Column(name = "ModifiedBy")
	private String modifiedBy;
	
	@Column(name = "ApprovedOn")
	private Timestamp approvedOn;
	
	@Column(name = "Active")
	private Boolean active;
	
	@Column(name = "RevokedOn")
	private Timestamp revokedOn;
	
	@Column(name = "RevokedBy")
	private String revokedBy;
	
	@Column(name = "Notes")
	private String notes;
	
	@Column(name = "CreatedByVendor")
	private Boolean createdByVendor;
}
