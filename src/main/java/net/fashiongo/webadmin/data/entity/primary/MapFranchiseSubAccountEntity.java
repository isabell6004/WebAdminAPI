package net.fashiongo.webadmin.data.entity.primary;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "Map_Franchise_SubAccount")
public class MapFranchiseSubAccountEntity {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "FranchiseSubAccountId")
	private Integer FranchiseSubAccountId;

	@Column(name = "FranchiseMasterAccountId")
	private int franchiseMasterAccountId;

	@Column(name = "RetailerId")
	private int retailerId;
}
