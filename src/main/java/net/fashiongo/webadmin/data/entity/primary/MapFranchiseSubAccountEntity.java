package net.fashiongo.webadmin.data.entity.primary;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@Entity
@Table(name = "Map_Franchise_SubAccount")
public class MapFranchiseSubAccountEntity implements Serializable {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "FranchiseSubAccountId")
	@Setter(AccessLevel.NONE)
	private Integer FranchiseSubAccountId;

	@Column(name = "FranchiseMasterAccountId")
	private int franchiseMasterAccountId;

	@Column(name = "RetailerId")
	private int retailerId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FranchiseMasterAccountId", referencedColumnName = "FranchiseMasterAccountId", insertable = false, updatable = false)
	private FranchiseMasterAccountEntity franchiseMasterAccount;

}
