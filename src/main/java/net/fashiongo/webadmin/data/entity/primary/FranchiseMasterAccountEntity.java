package net.fashiongo.webadmin.data.entity.primary;

import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Entity
@Table(name = "Franchise_MasterAccount")
public class FranchiseMasterAccountEntity implements Serializable {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "FranchiseMasterAccountId")
	private Integer franchiseMasterAccountId;

	@Column(name = "RetailerId")
	private int retailerId;

	@Column(name = "CreatedOn")
	private Timestamp createdOn;

	@Column(name = "CreatedBy")
	private String createdBy;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "FranchiseMasterAccountId",referencedColumnName = "FranchiseMasterAccountId", insertable = false, updatable = false)
	private List<MapFranchiseSubAccountEntity> mapFranchiseSubAccounts;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "RetailerId", referencedColumnName = "RetailerId", insertable = false, updatable = false)
	private RetailerEntity retailer;

	public LocalDateTime getCreatedOn() {
		return createdOn.toLocalDateTime();
	}
}
